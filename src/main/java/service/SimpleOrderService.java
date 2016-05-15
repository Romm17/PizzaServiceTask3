package service;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import infrastructure.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Usik
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    private PizzaRepository pizzaRepository;
    private OrderRepository orderRepository;

    public SimpleOrderService() {

    }

    @Autowired
    public SimpleOrderService(PizzaRepository pizzaRepository,
                              OrderRepository orderRepository)
    {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getOrders();
    }

    @Override
    @Benchmark
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {

        List<Pizza> pizzas = new ArrayList<>();

        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizzaByID(id)); // get Pizza from predifined in-memory list
        }

        Order newOrder = new Order();//getNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaList(pizzas);

        orderRepository.saveOrder(newOrder); // set Order Id and save Order to in-memory list
        return newOrder;

    }

    @Override
    public Order addPizzaToOrder(Order order, Integer pizzaId) {
        Order toUpdate = orderRepository.getOrderByID(order.getId());
        Pizza newPizza = pizzaRepository.getPizzaByID(pizzaId);
        if (newPizza != null)
            toUpdate.getPizzaList().add(pizzaRepository.getPizzaByID(pizzaId));
        return toUpdate;
    }

    @Override
    public Order removePizzaFromOrder(Order order, Integer pizzaId) {
        Order toUpdate = orderRepository.getOrderByID(order.getId());
        Pizza newPizza = pizzaRepository.getPizzaByID(pizzaId);
        if (newPizza != null)
            toUpdate.getPizzaList().remove(pizzaRepository.getPizzaByID(pizzaId));
        return toUpdate;
    }

    @Lookup
    protected Order getNewOrder(){
        return null;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public PizzaRepository getPizzaRepository() {
        return pizzaRepository;
    }

    public void setPizzaRepository(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

}


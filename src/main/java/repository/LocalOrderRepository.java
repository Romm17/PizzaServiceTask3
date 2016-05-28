package repository;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Roman Usik
 */
@Repository
public class LocalOrderRepository implements OrderRepository {

    private List<Order> orderList;

    public LocalOrderRepository() {

        orderList = new LinkedList<>();

    }

    public void init() {
        saveOrder(new Order(new Customer(1, "Roma"), new LinkedList<>()));
    }

    @Override
    public Integer saveOrder(Order toSave) {

        orderList.add(toSave);
        return 0;

    }

    @Override
    public Order getOrderByID(Integer id) {
        List<Order> orders = orderList.stream().filter(o -> Objects.equals(o.getId(), id)).collect(Collectors.toList());
        if (!orders.isEmpty())
            return orders.get(0);
        else
            return null;
    }

    @Override
    public List<Order> getOrders() {
        return orderList;
    }

    @Override
    public void updateOrder(Order order) {
        Order toUpdate = getOrderByID(order.getId());
        if (toUpdate != null) {
            toUpdate.setCreationTime(order.getCreationTime());
            toUpdate.setName(order.getName());
            toUpdate.setCustomer(order.getCustomer());
            toUpdate.setPizzaList(order.getPizzaList());
        }
    }

    @Override
    public void deleteOrder(Order order) {
        orderList.remove(order);
    }

}

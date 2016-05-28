package web;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OrderService;
import service.PizzaService;

import java.util.List;

/**
 * @author Roman Usik
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}/item", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getItemsInOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.getPizzaList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}/item/{pizzaId}", method = RequestMethod.PUT)
    public ResponseEntity<Order> addItemToOrder(@PathVariable("id") Integer id,
                                                      @PathVariable("pizzaId") Integer pizzaId) {
        Order order = orderService.getOrderById(id);
        Pizza pizza = pizzaService.getPizzaByID(pizzaId);
        if (order == null || pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.getPizzaList().add(pizza);
        order.setPrice(order.getPrice());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}/item/{pizzaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteItemFromOrder(@PathVariable("id") Integer id,
                                                @PathVariable("pizzaId") Integer pizzaId) {
        Order order = orderService.getOrderById(id);
        Pizza pizza = pizzaService.getPizzaByID(pizzaId);
        if (order == null || pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.getPizzaList().remove(pizza);
        order.setPrice(order.getPrice());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}/customer", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateCustomerInOrder(@PathVariable("id") Integer id,
                                                         @RequestBody Customer customer) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setCustomer(customer);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Integer> placeNewOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.placeNewOrder(orderDTO.getCustomer(), orderDTO.getPizzaList());
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.getId(), HttpStatus.CREATED);
    }

}

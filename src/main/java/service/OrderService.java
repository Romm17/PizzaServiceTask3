package service;

import domain.Customer;
import domain.Order;

import java.util.List;

/**
 * @author Roman Usik
 */
public interface OrderService {

    List<Order> getAllOrders();

    Order placeNewOrder(Customer customer, Integer... pizzasID);

    Order addPizzaToOrder(Order order, Integer pizzaId);

    Order removePizzaFromOrder(Order order, Integer pizzaId);
}

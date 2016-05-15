package repository;

import domain.Order;

import java.util.List;

/**
 * @author Roman Usik
 */
public interface OrderRepository {

    Integer saveOrder(Order order);

    Order getOrderByID(Integer id);

    List<Order> getOrders();

    void updateOrder(Order order);

    void deleteOrder(Order order);

}

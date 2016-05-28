import domain.Customer;
import domain.Order;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.PizzaRepository;
import service.OrderService;
import service.PizzaService;

import java.util.List;

/**
 * @author Roman Usik
 */
public class SpringPizzaApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);

        context.setParent(repositoryContext);
        context.refresh();

        OrderService orderService = context.getBean("orderService", OrderService.class);
        Customer customer = context.getBean(Customer.class, 1, "Roma");

        Order order = orderService.placeNewOrder(customer, 1, 2);
        order = orderService.addPizzaToOrder(order, 3);
        System.out.println("Price: " + order.getPrice());

        List<Order> orders = orderService.getAllOrders();
        orders.stream().forEach(System.out::println);

        repositoryContext.close();
        context.close();
    }
}

import domain.Customer;
import domain.Order;
import infrastructure.*;
import repository.LocalOrderRepository;
import repository.LocalPizzaRepository;
import repository.PizzaRepository;
import service.OrderService;
import service.SimpleOrderService;

/**
 * @author Roman Usik
 */
public class PizzaApp {

    public static void main(String[] args) throws Exception {

        Config config = new JavaConfig();

        ApplicationContext context = new JavaConfigApplicationContext(config);

        Customer customer = new Customer(1, "Customer");
        Order order;

        OrderService orderService = (OrderService) context.getBean("orderService");
//        OrderService orderService = new SimpleOrderService(new LocalPizzaRepository(), new LocalOrderRepository());
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);

    }

}

package infrastructure;

import repository.LocalOrderRepository;
import repository.LocalPizzaRepository;
import service.SimpleOrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Usik
 */
public class JavaConfig implements Config {

    private Map<String, Class<?>> beans;

    {
        beans = new HashMap<>();
        beans.put("pizzaRepository", LocalPizzaRepository.class);
        beans.put("orderRepository", LocalOrderRepository.class);
        beans.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImplementation(String beanName) {
        return beans.get(beanName);
    }

}

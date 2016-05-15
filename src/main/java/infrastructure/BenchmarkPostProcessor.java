package infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import service.OrderService;

import java.lang.reflect.Proxy;

/**
 * @author Roman Usik
 */
public class BenchmarkPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (!s.equals("orderService"))
            return o;
        Object res = new ProxyForBenchmark(o).createProxy();
        return res;
    }

}

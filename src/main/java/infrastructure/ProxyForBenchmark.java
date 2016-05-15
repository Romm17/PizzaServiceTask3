package infrastructure;

import service.OrderService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

/**
 * @author Roman Usik
 */
public class ProxyForBenchmark {

    private Object bean;

    public ProxyForBenchmark(Object bean) {
        this.bean = bean;
    }

    public Object createProxy() {
        Object proxedBean = Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                new Class[] {OrderService.class},
                (proxy, method, args) -> {
                    Object res = null;
                    Benchmark a = bean.getClass().getMethod(method.getName(),
                            method.getParameterTypes())
                            .getAnnotation(Benchmark.class);
                    if (a != null && a.active()) {
                        long time = System.currentTimeMillis();
                        res = method.invoke(bean, args);
                        time = System.currentTimeMillis() - time;
                        System.out.println("Method " + method.getName() + ", time : " + time + " ms.");
                    }
                    else {
                        res = method.invoke(bean, args);
                    }
                    return res;
                }
        );
        return proxedBean;
    }
}

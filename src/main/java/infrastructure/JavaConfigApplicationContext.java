package infrastructure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Usik
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config;
    private Map<String, Object> beans = new HashMap<>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public Object getBean(String beanName) {
        try {
            Object bean = beans.get(beanName);
            if (bean != null) {
                return bean;
            }

            BeanBuilder beanBuilder = new BeanBuilder(beanName);
            beanBuilder.createBean();
            beanBuilder.createProxy();
            beanBuilder.callInitMethod();
            bean = beanBuilder.build();

            beans.put(beanName, bean);
            return bean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class BeanBuilder {

        private Object bean;
        private final Class<?> type;

        BeanBuilder(String beanName) throws ClassNotFoundException {
            type = config.getImplementation(beanName);
        }

        void createProxy() {
            bean = new ProxyForBenchmark(bean).createProxy();
        }

        void callInitMethod() {
            try {
                Method m = bean.getClass().getDeclaredMethod("init");
                m.invoke(bean);
            } catch (NoSuchMethodException e) {

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Object build() {
            return bean;
        }

        void createBean() throws IllegalAccessException,
                InstantiationException,
                InvocationTargetException {
            Constructor<?> constructor = type.getConstructors()[0];
            if (constructor.getParameterCount() == 0) {
                bean = type.newInstance();
            } else {
                Object[] params = getParams(constructor);
                bean = constructor.newInstance(params);
            }
        }

        private Object[] getParams(Constructor<?> constructor) {
            Object[] params = new Object[constructor.getParameterCount()];
            Class[] types = constructor.getParameterTypes();
            for (int i = 0; i < types.length; i++) {
                Object param = getBeanByType(types[i]);
                params[i] = param;
            }
            return params;
        }

        private Object getBeanByType(Class type) {
            return getBean(getBeanNameByType(type));
        }

        private String getBeanNameByType(Class type) {
            String beanName = type.getSimpleName();
            StringBuilder sb = new StringBuilder(beanName);
            sb.setCharAt(0, Character.toLowerCase(beanName.charAt(0)));
            return sb.toString();
        }
    }
}

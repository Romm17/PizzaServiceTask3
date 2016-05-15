package infrastructure;

/**
 * @author Roman Usik
 */
public interface ApplicationContext {

    Object getBean(String beanName);
}

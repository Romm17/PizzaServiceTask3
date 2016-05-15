package infrastructure;

/**
 * @author Roman Usik
 */
public interface Config {

    Class<?> getImplementation(String beanName);

}

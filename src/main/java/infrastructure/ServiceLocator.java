package infrastructure;

/**
 * @author Roman Usik
 */
public interface ServiceLocator {

    Object lookUp(String beanName) throws Exception;
}

package beans.factory;

/**
 * Extension of BeanFactory to be implemented by bean factories that
 * can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients.
 * <br/>With the exception of getBeanDefinitionCount(), the methods
 * in this interface are not design for frequent invocation. Implementations
 * may be slow.
 * <br/>BeanFactory implementations that preload all their beans (for
 * example, DOM-based XML factories) may implement this interface.
 * <br/>This interface is discussed in "Expert One-on-One J2EE", by Rod Johnson.
 * @author  Rod Johnson
 * @since 16 April 2001
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * Return the number of beans defined in the factory
     * @return the number of beans defined in the factory
     */
    int getBeanDefinitionCount();

    /**
     * Return the names of all beans defined in this factory
     * @return the names of all beans defined in this factory.
     * Returns the empty String[], rather than null, if no beans are defined.
     */
    String[] getBeanDefinitionNames();

    /**
     * Return the names of beans matching the given object type
     * (including subclasses).
     * @param type class or interface to match
     * @return the names of beans matching the given object type
     * (including subclasses). Never returns null.
     */
    String[] getBeanDefinitionNames(Class type);

}

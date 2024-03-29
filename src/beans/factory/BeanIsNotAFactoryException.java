package beans.factory;


/**
 * Exception thrown when a bean is not a factory,
 * but a user tries to get at the factory for the given bean name.
 * Whether a bean is a factory is determined by whether it implements
 * the FactoryBean interface.
 * @author Rod Johnson
 * @since 10-Mar-2003
 * @see
 * @version $Revision: 1.1 $
 */
public class BeanIsNotAFactoryException extends BeanNotOfRequiredTypeException {

    public BeanIsNotAFactoryException(String name, Object actualInstance) {
        super(name, FactoryBean.class, actualInstance);
    }

}

package beans;

import java.beans.PropertyChangeEvent;

/**
 * Exception thrown on a type mismatch when trying to set a property.
 * @author Rod Johnson
 * @version $Revision: 1.4 $
 */
public class TypeMismatchException extends PropertyAccessException {

    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class requiredType, Throwable t) {
        super("Failed to convert property value of type [" +
                (propertyChangeEvent.getNewValue() != null ?
                        propertyChangeEvent.getNewValue().getClass().getName() : null) +
                "] to required type [" + requiredType.getName() + "]", propertyChangeEvent, t);
    }

}

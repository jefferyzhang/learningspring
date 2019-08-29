package aop.target;

/**
 * TargetSource that creates a new instance of the target bean for each request.
 * Can only be used in a bean factory.
 * @author Rod Johnson
 * @version $Id: PrototypeTargetSource.java,v 1.5 2004/03/18 02:46:13 trisberg Exp $
 */
public final class PrototypeTargetSource extends AbstractPrototypeTargetSource {

    public Object getTarget() {
        return newPrototypeInstance();
    }

    public void releaseTarget(Object target) {
        // Do nothing
    }

}

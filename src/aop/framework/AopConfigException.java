package aop.framework;

import core.NestedRuntimeException;

/**
 *
 * @author Rod Johnson
 * @since 13-Mar-2003
 * @version $Revision: 1.1 $
 */
public class AopConfigException extends NestedRuntimeException {

    /**
     * @param mesg
     */
    public AopConfigException(String mesg) {
        super(mesg);
    }


}

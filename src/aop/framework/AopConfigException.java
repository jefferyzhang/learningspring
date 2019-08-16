package aop.framework;

import core.NestedRuntimeException;

/**
 * Exception that gets thrown on illegal AOP configuration arguments.
 * @author Rod Johnson
 * @since 13-Mar-2003
 * @version $Id: AopConfigException.java,v 1.5 2004/03/18 02:46:05 trisberg Exp $
 */
public class AopConfigException extends NestedRuntimeException {

    public AopConfigException(String msg) {
        super(msg);
    }

    public AopConfigException(String msg, Throwable ex) {
        super(msg, ex);
    }

}

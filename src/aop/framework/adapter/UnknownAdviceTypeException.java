package aop.framework.adapter;

import aop.framework.AopConfigException;

/**
 * Exception thrown when an attempt is made to use an unsupported
 * Advisor or Advice type.
 * @author Rod Johnson
 * @version $Id: UnknownAdviceTypeException.java,v 1.4 2004/03/18 02:46:10 trisberg Exp $
 */
public class UnknownAdviceTypeException extends AopConfigException {

    public UnknownAdviceTypeException(Object advice) {
        super("No adapter for Advice of class '" + advice.getClass().getName() + "'");
    }

}

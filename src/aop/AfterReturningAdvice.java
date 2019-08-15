package aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;

/**
 * After returning advice is invoked only on normal method return, not if an
 * exception is thrown. Such advice can see the return value, but cannot change it.
 * @author Rod Johnson
 * @version $Id: AfterReturningAdvice.java,v 1.5 2004/03/21 15:15:40 jhoeller Exp $
 */
public interface AfterReturningAdvice extends Advice {

    void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable;

}

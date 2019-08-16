package aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Factory for method invocations.
 * @author Rod Johnson
 * @version $Id: MethodInvocationFactory.java,v 1.7 2004/03/18 02:46:05 trisberg Exp $
 */
public interface MethodInvocationFactory {

    MethodInvocation getMethodInvocation(Object proxy, Method method,
                                         Class targetClass, Object target, Object[] args,
                                         List interceptorsAndDynamicInterceptionAdvice, AdvisedSupport advised);

    void release(MethodInvocation mi);


}

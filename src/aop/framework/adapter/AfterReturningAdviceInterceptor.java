package aop.framework.adapter;

import aop.AfterReturningAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Interceptor to wrap a MethodAfterReturningAdvice. In future we may also offer a more efficient alternative
 * solution in cases where there is no interception advice and therefore no need to
 * create a MethodInvocation object.
 * <br>Used internally by the AOP framework: application developers should not need
 * to use this class directly.
 * @author Rod Johnson
 * @version $Id: AfterReturningAdviceInterceptor.java,v 1.3 2004/03/19 18:43:17 johnsonr Exp $
 */
public final class AfterReturningAdviceInterceptor implements MethodInterceptor {

    private AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object retval = mi.proceed();
        advice.afterReturning(retval, mi.getMethod(), mi.getArguments(), mi.getThis() );
        return retval;
    }

}

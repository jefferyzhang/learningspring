package aop.framework.adapter;

import aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Interceptor to wrap a MethodBeforeAdvice. In future we may also offer a more efficient alternative
 * solution in cases where there is no interception advice and therefore no need to
 * create a MethodInvocation object.
 * <br>Used internally by the AOP framework: application developers should not need
 * to use this class directly.
 * @author Rod Johnson
 * @version $Id: MethodBeforeAdviceInterceptor.java,v 1.2 2004/03/18 02:46:10 trisberg Exp $
 */
final class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation mi) throws Throwable {
        advice.before(mi.getMethod(), mi.getArguments(), mi.getThis() );
        return mi.proceed();
    }

}

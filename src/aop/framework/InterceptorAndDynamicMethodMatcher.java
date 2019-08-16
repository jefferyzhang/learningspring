package aop.framework;

import aop.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Internal framework class.
 * This class is required because if we put an Interceptor that implements InterceptionAdvice
 * in the interceptor list passed to MethodInvocationImpl, it may be mistaken for an
 * advice that requires dynamic method matching.
 * @author Rod Johnson
 * @see
 * @version $Id: InterceptorAndDynamicMethodMatcher.java,v 1.3 2004/03/18 02:46:05 trisberg Exp $
 */
class InterceptorAndDynamicMethodMatcher {

    final MethodMatcher methodMatcher;

    final MethodInterceptor interceptor;

    public InterceptorAndDynamicMethodMatcher(MethodInterceptor interceptor, MethodMatcher methodMatcher) {
        this.interceptor = interceptor;
        this.methodMatcher = methodMatcher;
    }

}

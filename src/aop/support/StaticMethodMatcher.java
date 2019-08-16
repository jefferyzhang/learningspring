package aop.support;

import aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * Convenient abstract superclas for static method matchers, which don't care
 * about arguments at runtime.
 */
public abstract class StaticMethodMatcher implements MethodMatcher {

    public final boolean isRuntime() {
        return false;
    }

    public final boolean matches(Method m, Class targetClass, Object[] args) {
        // Should never be invoked because isRuntime() returns false
        throw new UnsupportedOperationException("Illegal MethodMatcher usage");
    }

}

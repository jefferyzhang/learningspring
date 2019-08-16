package aop.support;

import aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * Convenient abstract superclass for dynamic method matchers,
 * which do care about arguments at runtime.
 */
public abstract class DynamicMethodMatcher implements MethodMatcher {

    public final boolean isRuntime() {
        return true;
    }

    /**
     * Can override to add preconditions for dynamic matching. This implementation
     * always returns true.
     */
    public boolean matches(Method m, Class targetClass) {
        return true;
    }

}

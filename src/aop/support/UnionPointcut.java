package aop.support;

import aop.ClassFilter;
import aop.MethodMatcher;
import aop.Pointcut;

import java.lang.reflect.Method;

/**
 * Pointcut unions are tricky, because we can't just
 * OR the MethodMatchers: we need to check that each MethodMatcher's
 * ClassFilter was happy as well.
 * @author Rod Johnson
 * @version $Id: UnionPointcut.java,v 1.2 2004/03/18 02:46:11 trisberg Exp $
 */
class UnionPointcut implements Pointcut {

    private final Pointcut a;

    private final Pointcut b;

    private MethodMatcher mm;

    public UnionPointcut(Pointcut a, Pointcut b) {
        this.a = a;
        this.b = b;
        this.mm = new PointcutUnionMethodMatcher();
    }


    public ClassFilter getClassFilter() {
        return ClassFilters.union(a.getClassFilter(), b.getClassFilter());
    }

    public MethodMatcher getMethodMatcher() {
        // Complicated: we need to consider both class filter and method matcher
        return mm;
    }

    private class PointcutUnionMethodMatcher implements MethodMatcher {

        public boolean matches(Method m, Class targetClass) {
            return (a.getClassFilter().matches(targetClass) && a.getMethodMatcher().matches(m, targetClass)) ||
                    (b.getClassFilter().matches(targetClass) && b.getMethodMatcher().matches(m, targetClass));
        }

        public boolean isRuntime() {
            return a.getMethodMatcher().isRuntime() || b.getMethodMatcher().isRuntime();
        }

        public boolean matches(Method m, Class targetClass, Object[] args) {
            // 2-arg matcher will already have run, so we don't need to do class filtering again
            return a.getMethodMatcher().matches(m, targetClass, args) || b.getMethodMatcher().matches(m, targetClass, args);
        }

    }

}

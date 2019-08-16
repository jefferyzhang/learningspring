package aop.support;

import aop.MethodMatcher;
import aop.Pointcut;

import java.lang.reflect.Method;

/**
 * Static methods useful for manipulating and evaluating pointcuts.
 * This methods are particularly useful for composing pointcuts
 * using the union and intersection methods.
 * @author Rod Johnson
 * @version $Id: Pointcuts.java,v 1.5 2004/03/18 02:46:11 trisberg Exp $
 */
public abstract class Pointcuts {

    public static Pointcut union(Pointcut a, Pointcut b) {
        return new UnionPointcut(a, b);
    }

    public static Pointcut intersection(Pointcut a, Pointcut b) {
        return new ComposablePointcut(a.getClassFilter(), a.getMethodMatcher()).intersection(b);
    }

    /**
     * Perform the least expensive check for a match.
     */
    public static boolean matches(Pointcut pc, Method m, Class targetClass, Object[] arguments) {
        if (pc == Pointcut.TRUE) {
            return true;
        }

        if (pc.getClassFilter().matches(targetClass)) {
            // Only check if it gets past first hurdle
            MethodMatcher mm = pc.getMethodMatcher();
            if (mm.matches(m, targetClass)) {
                // We may need additional runtime (argument) check
                return  mm.isRuntime() ? mm.matches(m, targetClass, arguments) : true;
            }
        }
        return false;
    }

    public static boolean equals(Pointcut a, Pointcut b) {
        return a.getClassFilter() == b.getClassFilter() &&
                a.getMethodMatcher() == b.getMethodMatcher();
    }

}

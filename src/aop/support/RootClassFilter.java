package aop.support;

import aop.ClassFilter;

/**
 * Simple ClassFilter implementation that passes classes (and optionally subclasses)
 */
public class RootClassFilter implements ClassFilter {

    private Class clazz;

    // TODO inheritance

    public RootClassFilter(Class clazz) {
        this.clazz = clazz;
    }

    public boolean matches(Class candidate) {
        return clazz.isAssignableFrom(candidate);
    }
}

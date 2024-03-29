package aop.support;

import aop.ClassFilter;
import aop.MethodMatcher;
import aop.Pointcut;

/**
 * Convenient class for building up pointcuts.
 * All methods return ComposablePointcut, so we can use a concise
 * idiom like:
 * <code>
 * Pointcut pc = new ComposablePointcut().union(classFilter).intersection(methodMatcher).intersection(pointcut);
 * </code>
 * There is no union() method on this class. Use the Pointcuts.union()
 * method for this.
 * @author Rod Johnson
 * @since 11-Nov-2003
 * @version $Id: ComposablePointcut.java,v 1.5 2004/03/18 02:46:11 trisberg Exp $
 */
public class ComposablePointcut implements Pointcut {

    private ClassFilter classFilter;

    private MethodMatcher methodMatcher;

    public ComposablePointcut() {
        this.classFilter =  ClassFilter.TRUE;
        this.methodMatcher = MethodMatcher.TRUE;
    }

    public ComposablePointcut(ClassFilter classFilter, MethodMatcher methodMatcher) {
        this.classFilter = classFilter;
        this.methodMatcher = methodMatcher;
    }

    public ComposablePointcut union(ClassFilter filter) {
        this.classFilter = ClassFilters.union(this.classFilter, filter);
        return this;
    }

    public ComposablePointcut intersection(ClassFilter filter) {
        this.classFilter = ClassFilters.intersection(this.classFilter, filter);
        return this;
    }

    public ComposablePointcut union(MethodMatcher mm) {
        this.methodMatcher = MethodMatchers.union(this.methodMatcher, mm);
        return this;
    }

    public ComposablePointcut intersection(MethodMatcher mm) {
        this.methodMatcher = MethodMatchers.intersection(this.methodMatcher, mm);
        return this;
    }


    public ComposablePointcut intersection(Pointcut other) {
        this.classFilter = ClassFilters.intersection(this.classFilter, other.getClassFilter());
        this.methodMatcher = MethodMatchers.intersection(this.methodMatcher, other.getMethodMatcher());
        return this;
    }

    public ClassFilter getClassFilter() {
        return this.classFilter;
    }

    public MethodMatcher getMethodMatcher() {
        return this.methodMatcher;
    }

}

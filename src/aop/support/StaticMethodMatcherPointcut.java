package aop.support;

import aop.ClassFilter;
import aop.MethodMatcher;
import aop.Pointcut;

/**
 * Convenient superclass when we want to force subclasses to
 * implement MethodMatcher interface, but subclasses
 * will want to be pointcuts. The getClassFilter() method can
 * be overriden to customize ClassFilter behaviour as well.
 * @author Rod Johnson
 * @version $Id: StaticMethodMatcherPointcut.java,v 1.4 2004/03/18 02:46:11 trisberg Exp $
 */
public abstract class StaticMethodMatcherPointcut extends StaticMethodMatcher implements Pointcut {

    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    public final MethodMatcher getMethodMatcher() {
        return this;
    }

}

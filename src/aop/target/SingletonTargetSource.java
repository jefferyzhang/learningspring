package aop.target;

import aop.TargetSource;

/**
 * Implementation of the TargetSource interface that
 * holds a local object. This is the default implementation of TargetSource
 * used by the AOP framework. There is no need to create objects of this
 * class in application code.
 * @author Rod Johnson
 * @version $Id: SingletonTargetSource.java,v 1.5 2004/03/18 02:46:13 trisberg Exp $
 */
public final class SingletonTargetSource implements TargetSource {

    /** Target cached and invoked using reflection */
    private Object target;

    public SingletonTargetSource(Object target) {
        this.target = target;
    }

    public Class getTargetClass() {
        return target.getClass();
    }

    public Object getTarget() {
        return this.target;
    }

    public void releaseTarget(Object o) {
    }

    public String toString() {
        return "Singleton target source (not dynamic): target=[" + target + "]";
    }



    public boolean isStatic() {
        return true;
    }

    /**
     * Two invoker interceptors are equal if they have the same target or if the targets
     * or the targets are equal.
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof SingletonTargetSource))
            return false;
        SingletonTargetSource b = (SingletonTargetSource) other;
        if (this.target == null)
            return b.target == null;
        return this.target.equals(b.target);
    }
}

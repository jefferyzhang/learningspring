package aop.target;

import aop.TargetSource;
import aop.framework.AopConfigException;

/**
 * Implementation of TargetSource interface that caches a local target object,
 * but allows the target to be swapped while the application is running.
 *
 * <p>If configuring an object of this class in a Spring IoC container,
 * use constructor injection.
 *
 * @author Rod Johnson
 * @version $Id: HotSwappableTargetSource.java,v 1.6 2004/03/23 20:17:00 jhoeller Exp $
 */
public class HotSwappableTargetSource implements TargetSource {

    /** Target cached and invoked using reflection */
    private Object target;

    /**
     * Create a new HotSwappableTargetSource with the initial target.
     * @param initialTarget initial target
     */
    public HotSwappableTargetSource(Object initialTarget) {
        this.target = initialTarget;
    }

    public Class getTargetClass() {
        return target.getClass();
    }


    public final boolean isStatic() {
        return false;
    }

    /**
     * Synchronization around something that takes so little time is fine
     */
    public final synchronized Object getTarget() {
        return this.target;
    }

    /**
     */
    public void releaseTarget(Object o) {
    }

    /**
     * Swap the target, returning the old target.
     * @param newTarget new target
     * @return the old target
     * @throws AopConfigException if the new target is invalid
     */
    public synchronized Object swap(Object newTarget) throws AopConfigException {
        if (newTarget == null) {
            throw new AopConfigException("Cannot swap to null");
        }
        // TODO type checks
        Object old = this.target;
        this.target = newTarget;
        return old;
    }

    /**
     * Two invoker interceptors are equal if they have the same target or
     * if the targets are equal.
     */
    public boolean equals(Object other) {
        if (!(other instanceof HotSwappableTargetSource)) {
            return false;
        }
        HotSwappableTargetSource otherTargetSource = (HotSwappableTargetSource) other;
        return otherTargetSource.target == this.target || otherTargetSource.target.equals(this.target);
    }

}

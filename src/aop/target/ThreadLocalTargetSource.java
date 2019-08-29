package aop.target;

import aop.IntroductionAdvisor;
import aop.support.DefaultIntroductionAdvisor;
import aop.support.DelegatingIntroductionInterceptor;
import beans.factory.DisposableBean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Alternative to an object pool. This TargetSource uses a threading model in which
 * every thread has its own copy of the target. There's no contention for targets.
 * Target object creation is kept to a minimum on the running server.
 *
 * <p>Application code is written as to a normal pool; callers can't assume they
 * will be dealing with the same instance in invocations in different threads.
 * However, state can be relied on during the operations of a single thread:
 * for example, if one caller makes repeated calls on the AOP proxy.
 *
 * <p>Cleanup is performed in the destroy() method from DisposableBean.
 *
 * @author Rod Johnson
 * @version $Id: ThreadLocalTargetSource.java,v 1.8 2004/03/18 02:46:13 trisberg Exp $
 */
public final class ThreadLocalTargetSource extends AbstractPrototypeTargetSource implements ThreadLocalTargetSourceStats, DisposableBean {

    /**
     * ThreadLocal holding the target associated with the current
     * thread. Unlike most ThreadLocals, which are static, this variable
     * is meant to be per thread per instance of the ThreadLocalTargetSource class.
     */
    private ThreadLocal targetInThread = new ThreadLocal();

    /**
     * Set of managed targets, enabling us to keep track
     * of the targets we've created.
     */
    private Set targetSet = new HashSet();

    private int invocations;

    private int hits;

    /**
     * Implementation of abstract getTarget() method.
     * We look for a target held in a ThreadLocal. If
     * we don't find one, we create one and bind it to the thread.
     * No synchronization is required.
     */
    public Object getTarget() {
        ++invocations;
        Object target = targetInThread.get();
        if (target == null) {
            logger.info("No target for apartment prototype '" + getTargetBeanName() +
                    "' found in thread: creating one and binding it to thread '" + Thread.currentThread().getName() + "'");
            // Associate target with thread local
            target = newPrototypeInstance();
            targetInThread.set(target);
            targetSet.add(target);
        }
        else {
            ++hits;
        }

        return target;
    }


    public void releaseTarget(Object o) {
        // Do nothing
    }

    /**
     * Dispose of targets if necessary;
     * clear ThreadLocal.
     */
    public void destroy() {
        logger.info("Destroying ThreadLocal bindings");
        for (Iterator itr = this.targetSet.iterator(); itr.hasNext(); ) {
            Object target = itr.next();
            if (target instanceof DisposableBean) {
                try {
                    ((DisposableBean) target).destroy();
                }
                catch (Exception ex) {
                    // Do nothing
                    logger.warn("Thread-bound target of class '" + target.getClass() +
                            "' threw exception from destroy() method", ex);
                }
            }
        }
        this.targetSet.clear();

        // Clear ThreadLocal
        this.targetInThread = null;
    }

    public int getInvocations() {
        return invocations;
    }

    public int getHits() {
        return hits;
    }

    public int getObjects() {
        return targetSet.size();
    }

    /**
     * Return an introduction advisor mixin that allows the AOP proxy to be
     * case to ThreadLocalInvokerStats.
     */
    public IntroductionAdvisor getStatsMixin() {
        DelegatingIntroductionInterceptor dii = new DelegatingIntroductionInterceptor(this);
        return new DefaultIntroductionAdvisor(dii, ThreadLocalTargetSourceStats.class);
    }

}

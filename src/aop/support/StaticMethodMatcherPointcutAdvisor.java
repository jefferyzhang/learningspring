package aop.support;

import aop.Pointcut;
import aop.PointcutAdvisor;
import core.Ordered;
import org.aopalliance.aop.Advice;

/**
 * Convenient superclass for Advisors that are also static pointcuts.
 * @author Rod Johnson
 * @version $Id: StaticMethodMatcherPointcutAdvisor.java,v 1.7 2004/03/23 14:29:45 jhoeller Exp $
 */
public abstract class StaticMethodMatcherPointcutAdvisor extends StaticMethodMatcherPointcut
        implements PointcutAdvisor, Ordered {

    private int order = Integer.MAX_VALUE;

    private Advice advice;

    public StaticMethodMatcherPointcutAdvisor() {
    }

    public StaticMethodMatcherPointcutAdvisor(Advice advice) {
        this.advice = advice;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Advice getAdvice() {
        return advice;
    }

    public Pointcut getPointcut() {
        return this;
    }

    public boolean isPerInstance() {
        throw new UnsupportedOperationException("perInstance property of Advisor is not yet supported in Spring");
    }

}

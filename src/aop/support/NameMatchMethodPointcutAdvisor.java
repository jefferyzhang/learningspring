package aop.support;

import aop.Pointcut;
import aop.PointcutAdvisor;
import core.Ordered;
import org.aopalliance.aop.Advice;

/**
 * Convenient class for name-match method pointcuts that hold an Interceptor,
 * making them an Advisor.
 * @author Juergen Hoeller
 * @version $Id: NameMatchMethodPointcutAdvisor.java,v 1.5 2004/03/23 14:29:45 jhoeller Exp $
 */
public class NameMatchMethodPointcutAdvisor extends NameMatchMethodPointcut
        implements PointcutAdvisor, Ordered {

    private int order = Integer.MAX_VALUE;

    private Advice advice;

    public NameMatchMethodPointcutAdvisor() {
    }

    public NameMatchMethodPointcutAdvisor(Advice advice) {
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

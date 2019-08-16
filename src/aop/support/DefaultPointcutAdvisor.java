package aop.support;

import aop.Pointcut;
import aop.PointcutAdvisor;
import core.Ordered;
import org.aopalliance.aop.Advice;

/**
 * Convenient pointcut-driven advisor implementation, implementing
 * the getPointcut() and isPerInstance() methods.
 *
 * <p>This is the most commonly used Advisor implementation. It can be
 * used with any pointcut and advice type, except for introductions.
 *
 * @author Rod Johnson
 * @version $Id: DefaultPointcutAdvisor.java,v 1.7 2004/03/23 14:29:45 jhoeller Exp $
 */
public class DefaultPointcutAdvisor implements PointcutAdvisor, Ordered {

    private int order = Integer.MAX_VALUE;

    private Pointcut pointcut;

    private Advice advice;

    public DefaultPointcutAdvisor() {
    }

    public DefaultPointcutAdvisor(Advice advice) {
        this(Pointcut.TRUE, advice);
    }

    public DefaultPointcutAdvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
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
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public boolean isPerInstance() {
        throw new UnsupportedOperationException("perInstance property of Advisor is not yet supported in Spring");
    }

    public boolean equals(Object o) {
        if (!(o instanceof DefaultPointcutAdvisor)) {
            return false;
        }
        DefaultPointcutAdvisor other = (DefaultPointcutAdvisor) o;
        return other.advice.equals(this.advice) && other.pointcut.equals(this.pointcut);
    }

    public String toString() {
        return "DefaultPointcutAdvisor: pointcut=[" + pointcut + "] advice=[" + advice + "]";
    }

}

package aop;

/**
 * Superinterface for all Advisors that are driven by a pointcut.
 * This covers nearly all advisors except introduction advisors,
 * for which method-level matching doesn't apply.
 * @author Rod Johnson
 * @version $Id: PointcutAdvisor.java,v 1.3 2004/03/18 02:46:07 trisberg Exp $
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}

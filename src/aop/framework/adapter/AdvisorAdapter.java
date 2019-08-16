package aop.framework.adapter;

import aop.Advisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;

/**
 * Interface allowing extension to the Spring AOP framework to allow
 * handling of new Advisors and Advice types.
 * Implementing objects can create AOP Alliance Interceptors from
 * custom advice types, enabling these advice types to be used
 * in the Spring AOP framework, which uses interception under the covers.
 * <br>There is no need for most Spring users to implement this interface;
 * do so only if you need to introduce more Advisor or Advice types to
 * Spring.
 * @author Rod Johnson
 * @version $Id: AdvisorAdapter.java,v 1.7 2004/03/19 16:54:41 johnsonr Exp $
 */
public interface AdvisorAdapter {

    /**
     * Does this adapter understand this advice object?
     * Is it valid to invoke the wrap() method with the
     * given advice as an argument?
     * @param advice Advice such as a BeforeAdvice.
     * @return whether this adapter understands the given advice object
     */
    boolean supportsAdvice(Advice advice);

    /**
     * Return an AOP Alliance Interceptor exposing the behaviour of
     * the given advice to an interception-based AOP framework.
     * Don't worry about any Pointcut contained in the Advisor;
     * the AOP framework will take care of checking the pointcut.
     * @param advisor Advisor. the supportsAdvisor() method must have
     * returned true on this object
     * @return an AOP Alliance interceptor for this Advisor. There's
     * no need to cache instances for efficiency, as the AOP framework
     * caches advice chains.
     */
    Interceptor getInterceptor(Advisor advisor);

}

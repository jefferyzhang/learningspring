package aop.framework.adapter;

import aop.Advisor;
import aop.AfterReturningAdvice;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;

/**
 * Adapter to enable AfterReturningAdvisor and MethodAfterReturningAdvice
 * to be used in the Spring AOP framework.
 * <br>This involves wrapping these advice types in interceptors.
 * @author Rod Johnson
 * @version $Id: AfterReturningAdviceAdapter.java,v 1.7 2004/03/19 18:43:17 johnsonr Exp $
 */
class AfterReturningAdviceAdapter implements AdvisorAdapter {


    public boolean supportsAdvice(Advice advice) {
        return advice instanceof AfterReturningAdvice;
    }



    public Interceptor getInterceptor(Advisor advisor) {
        AfterReturningAdvice advice = (AfterReturningAdvice) advisor.getAdvice();
        return new AfterReturningAdviceInterceptor(advice);
    }

}

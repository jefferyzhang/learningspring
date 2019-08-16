package aop.framework.adapter;

import aop.Advisor;
import aop.ThrowsAdvice;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;

/**
 *
 * @author Rod Johnson
 * @version $Id: ThrowsAdviceAdapter.java,v 1.5 2004/03/19 16:54:41 johnsonr Exp $
 */
class ThrowsAdviceAdapter implements AdvisorAdapter {


    public boolean supportsAdvice(Advice advice) {
        return advice instanceof ThrowsAdvice;
    }


    public Interceptor getInterceptor(Advisor advisor) {
        return new ThrowsAdviceInterceptor(advisor.getAdvice());
    }

}

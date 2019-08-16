package aop.framework.adapter;

import aop.Advisor;
import aop.MethodBeforeAdvice;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;

/**
 *
 * @author Rod Johnson
 * @version $Id: BeforeAdviceAdapter.java,v 1.5 2004/03/19 16:54:41 johnsonr Exp $
 */
class BeforeAdviceAdapter implements AdvisorAdapter {


    public boolean supportsAdvice(Advice advice) {
        return advice instanceof MethodBeforeAdvice;
    }


    public Interceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
        return new MethodBeforeAdviceInterceptor(advice) ;
    }

}

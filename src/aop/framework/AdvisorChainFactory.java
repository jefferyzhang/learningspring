package aop.framework;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Factory for advisor chains.
 * @author Rod Johnson
 * @version $Id: AdvisorChainFactory.java,v 1.4 2004/03/18 02:46:05 trisberg Exp $
 */
public interface AdvisorChainFactory extends AdvisedSupportListener {

    /**
     * Return a list of Interceptor and InterceptorAndDynamicMethodMatcher.
     */
    List getInterceptorsAndDynamicInterceptionAdvice(Advised pc, Object proxy, Method method, Class targetClass);

}

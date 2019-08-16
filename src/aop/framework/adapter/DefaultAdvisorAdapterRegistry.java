package aop.framework.adapter;

import aop.Advisor;
import aop.support.DefaultPointcutAdvisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rod Johnson
 * @version $Id: DefaultAdvisorAdapterRegistry.java,v 1.10 2004/03/19 16:54:41 johnsonr Exp $
 */
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry {

    private List adapters = new LinkedList();

    public DefaultAdvisorAdapterRegistry() {
        // register well-known adapters
        registerAdvisorAdapter(new BeforeAdviceAdapter());
        registerAdvisorAdapter(new AfterReturningAdviceAdapter());
        registerAdvisorAdapter(new ThrowsAdviceAdapter());
    }

    public Advisor wrap(Object adviceObject) throws UnknownAdviceTypeException {
        if (adviceObject instanceof Advisor) {
            return (Advisor) adviceObject;
        }

        if (!(adviceObject instanceof Advice)) {
            throw new UnknownAdviceTypeException(adviceObject);
        }
        Advice advice = (Advice) adviceObject;

        if (advice instanceof Interceptor) {
            // So well-known it doesn't even need an adapter
            return new DefaultPointcutAdvisor(advice);
        }
        for (int i = 0; i < this.adapters.size(); i++) {
            // Check that it is supported
            AdvisorAdapter adapter = (AdvisorAdapter) this.adapters.get(i);
            if (adapter.supportsAdvice(advice)) {
                return new DefaultPointcutAdvisor(advice);
            }
        }
        throw new UnknownAdviceTypeException(advice);
    }

    public Interceptor getInterceptor(Advisor advisor) throws UnknownAdviceTypeException {
        Advice advice = advisor.getAdvice();
        if (advice instanceof Interceptor) {
            return (Interceptor) advice;
        }
        for (int i = 0; i < this.adapters.size(); i++) {
            AdvisorAdapter adapter = (AdvisorAdapter) this.adapters.get(i);
            if (adapter.supportsAdvice(advice)) {
                return adapter.getInterceptor(advisor);
            }
        }
        throw new UnknownAdviceTypeException(advisor.getAdvice());
    }

    public void registerAdvisorAdapter(AdvisorAdapter adapter) {
        this.adapters.add(adapter);
    }

}

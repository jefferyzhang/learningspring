package aop.framework;

import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdvisorChainFactory implementation that caches by method.
 * Uses IdentityHashMap in JVM 1.4, which skips expensive Method.hashCode()
 * call. In 1.3, falls back to using HashMap.
 * @author Rod Johnson
 * @version $Id: HashMapCachingAdvisorChainFactory.java,v 1.4 2004/03/18 02:46:05 trisberg Exp $
 */
public final class HashMapCachingAdvisorChainFactory implements AdvisorChainFactory {

    private Map methodCache = createMap();

    private Map createMap() {
        // Use IdentityHashMap, introduced in Java 1.4, which is a lot faster
        // as we want to compare Method keys by reference.
        // The reason we do this via reflection rather than using new is to avoid a dependence in this
        // class that will break it under 1.3
        try {
            Class clazz = Class.forName("java.util.IdentityHashMap");
            return (Map) clazz.newInstance();
        }
        catch (Exception ex) {
            // Shouldn't happen
            LogFactory.getLog(getClass()).debug("Falling back to HashMap (JDK 1.3?): couldn't create an IdentityHashMap using reflection", ex);
            return new HashMap();
        }
    }

    public List getInterceptorsAndDynamicInterceptionAdvice(Advised config, Object proxy, Method method, Class targetClass) {
        List cached = (List) this.methodCache.get(method);
        if (cached == null) {
            // Recalculate
            cached = AdvisorChainFactoryUtils.calculateInterceptorsAndDynamicInterceptionAdvice(config, proxy, method, targetClass);
            this.methodCache.put(method, cached);
        }
        return cached;
    }



    public void activated(AdvisedSupport advisedSupport) {
    }



    public void adviceChanged(AdvisedSupport advisedSupport) {
        methodCache.clear();
    }

}

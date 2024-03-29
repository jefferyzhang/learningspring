package aop.support;

import aop.ClassFilter;
import aop.IntroductionAdvisor;
import aop.IntroductionInterceptor;
import aop.framework.AopConfigException;
import core.Ordered;
import org.aopalliance.aop.Advice;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple IntroductionAdvisor implementation that by default applies to any class.
 * @author Rod Johnson
 * @since 11-Nov-2003
 * @version $Id: DefaultIntroductionAdvisor.java,v 1.4 2004/03/23 14:29:45 jhoeller Exp $
 */
public class DefaultIntroductionAdvisor implements IntroductionAdvisor, ClassFilter, Ordered {

    private int order = Integer.MAX_VALUE;

    private IntroductionInterceptor interceptor;

    private Set interfaces = new HashSet();

    public DefaultIntroductionAdvisor(IntroductionInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public DefaultIntroductionAdvisor(IntroductionInterceptor interceptor, Class clazz) throws AopConfigException {
        this(interceptor);
        addInterface(clazz);
    }

    /**
     * Wrap this interceptor and introduce all interfaces.
     */
    public DefaultIntroductionAdvisor(DelegatingIntroductionInterceptor dii) {
        this((IntroductionInterceptor) dii);
        for (int i = 0; i < dii.getIntroducedInterfaces().length; i++) {
            Class intf = dii.getIntroducedInterfaces()[i];
            addInterface(intf);
        }
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void addInterface(Class intf) throws AopConfigException {
        this.interfaces.add(intf);
    }

    public ClassFilter getClassFilter() {
        return this;
    }

    public Advice getAdvice() {
        return interceptor;
    }

    public Class[] getInterfaces() {
        return (Class[]) this.interfaces.toArray(new Class[this.interfaces.size()]);
    }

    public boolean matches(Class clazz) {
        return true;
    }

    /**
     * Default for an introduction is per-instance interception.
     */
    public boolean isPerInstance() {
        return true;
    }

    public void validateInterfaces() throws AopConfigException {
        for (int i = 0; i < getInterfaces().length; i++) {
            if (!getInterfaces()[i].isInterface()) {
                throw new AopConfigException("Class '" + getInterfaces()[i].getName() + "' is not an interface; cannot be used in an introduction");
            }
            if (!interceptor.implementsInterface(getInterfaces()[i])) {
                throw new AopConfigException("IntroductionInterceptor [" + interceptor + "] " +
                        "does not implement interface '" + getInterfaces()[i].getName() + "' specified in introduction advice");
            }
        }
    }

}

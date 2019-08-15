package test;

import beans.BeanUtils;
import beans.BeansException;
import beans.factory.FactoryBean;
import beans.factory.support.AbstractFactoryBean;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 14 10:49
 */
public class PerfFactoryBean extends AbstractFactoryBean implements FactoryBean {

    private static Class<?> interfaceClazz;

    public static void proxyConfig(Class<?> interfaceClazz){
        PerfFactoryBean.interfaceClazz = interfaceClazz;
    }

    private Object target;

    @Override
    public Object getObject() throws BeansException {
        PerfProxy perfProxy = new PerfProxy();
        Object proxy = perfProxy.bind(interfaceClazz, target);

        return proxy;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}

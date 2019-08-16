package aop.framework;

/**
 * Simple implementation of AopProxyFactory
 * @author Rod Johnson
 * @version $Id: DefaultAopProxyFactory.java,v 1.2 2004/03/18 02:46:05 trisberg Exp $
 */
public class DefaultAopProxyFactory implements AopProxyFactory {


    public AopProxy createAopProxy(AdvisedSupport advisedSupport) throws AopConfigException {
        boolean useCglib = advisedSupport.getOptimize() || advisedSupport.getProxyTargetClass() || advisedSupport.getProxiedInterfaces().length == 0;
        if (useCglib) {
            return CglibProxyFactory.createCglibProxy(advisedSupport);
        }
        else {
            // Depends on whether we have expose proxy or frozen or static ts
            return new JdkDynamicAopProxy(advisedSupport);
        }
    }

    /**
     * Inner class to just introduce a CGLIB dependency
     * when actually creating a CGLIB proxy.
     */
    private static class CglibProxyFactory {

        private static AopProxy createCglibProxy(AdvisedSupport advisedSupport) {
            throw new RuntimeException("吧cglib2aopproxy引入");
            //return new Cglib2AopProxy(advisedSupport);
        }
    }

}

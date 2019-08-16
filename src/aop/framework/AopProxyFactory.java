package aop.framework;

/**
 * Interface to be implemented by objects that can create
 * AOP proxies based on AdvisedSupport objects
 * @author Rod Johnson
 * @version $Id: AopProxyFactory.java,v 1.2 2004/03/18 02:46:05 trisberg Exp $
 */
public interface AopProxyFactory {

    /**
     * Return an AopProxy for the given AdvisedSupport object
     * @param advisedSupport AOP configuration
     * @return an AOP proxy
     * @throws AopConfigException if the configuration is invalid
     */
    AopProxy createAopProxy(AdvisedSupport advisedSupport) throws AopConfigException;

}

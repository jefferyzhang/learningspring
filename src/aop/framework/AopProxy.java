package aop.framework;

/**
 *
 * @author Rod Johnson
 * @version $Id: AopProxy.java,v 1.17 2004/03/18 02:46:05 trisberg Exp $
 */
public interface AopProxy {
    /**
     * Creates a new Proxy object for the given object, proxying
     * the given interface. Uses the thread context class loader.
     */
    public abstract Object getProxy();
    /**
     * Creates a new Proxy object for the given object, proxying
     * the given interface. Uses the given class loader.
     */
    public abstract Object getProxy(ClassLoader cl);
}

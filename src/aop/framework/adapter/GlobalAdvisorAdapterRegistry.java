package aop.framework.adapter;

/**
 * Singleton to publish a shared DefaultAdvisorAdapterRegistry.
 * @author Rod Johnson
 * @version $Id: GlobalAdvisorAdapterRegistry.java,v 1.3 2004/03/18 02:46:10 trisberg Exp $
 */
public class GlobalAdvisorAdapterRegistry extends DefaultAdvisorAdapterRegistry {

    private static GlobalAdvisorAdapterRegistry instance = new GlobalAdvisorAdapterRegistry();

    /**
     * @return the per-VM AdapterRegistry instance.
     */
    public static GlobalAdvisorAdapterRegistry getInstance() {
        return instance;
    }

    /**
     * Constructor to enforce the Singleton pattern.
     */
    private GlobalAdvisorAdapterRegistry() {
    }

}

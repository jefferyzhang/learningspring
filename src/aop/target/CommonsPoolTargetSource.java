package aop.target;

import beans.factory.BeanFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Jakarta Commons pooling implementation extending AbstractPoolingInvokerInterceptor
 * @author Rod Johnson
 * @version $Id: CommonsPoolTargetSource.java,v 1.4 2004/03/18 02:46:13 trisberg Exp $
 */
public class CommonsPoolTargetSource extends AbstractPoolingTargetSource
        implements PoolableObjectFactory {

    /**
     * Jakarta Commons object pool
     */
    private ObjectPool pool;

    protected final void createPool(BeanFactory beanFactory) {
        logger.info("Creating Commons object pool");
        this.pool = createObjectPool();
    }

    /**
     * Subclasses can override this if they want to return a different
     * Commons pool to GenericObject pool.
     * They should apply properties to the pool here
     * @return an empty Commons pool
     */
    protected ObjectPool createObjectPool() {
        GenericObjectPool gop = new GenericObjectPool(this);
        gop.setMaxActive(getMaxSize());
        return gop;
    }

    public Object getTarget() throws Exception {
        return this.pool.borrowObject();
    }

    public void releaseTarget(Object target) throws Exception {
        this.pool.returnObject(target);
    }

    public int getActive() {
        return this.pool.getNumActive();
    }

    public int getFree() {
        return this.pool.getNumIdle();
    }


    //---------------------------------------------------------------------
    // Implementation of DisposableBean interface
    //---------------------------------------------------------------------
    public void destroy() throws Exception {
        logger.info("Closing Commons pool");
        this.pool.close();
    }


    //----------------------------------------------------------------------------
    // Implementation of org.apache.commons.pool.PoolableObjectFactory interface
    //----------------------------------------------------------------------------

    /**
     * @see org.apache.commons.pool.PoolableObjectFactory#makeObject()
     */
    public Object makeObject() {
        return newPrototypeInstance();
    }

    /**
     * @see org.apache.commons.pool.PoolableObjectFactory#destroyObject(java.lang.Object)
     */
    public void destroyObject(Object o) throws Exception {
       // if (o instanceof DisposableBean) {
          //  ((DisposableBean) o).destroy();
       // }
    }

    /**
     * @see org.apache.commons.pool.PoolableObjectFactory#validateObject(java.lang.Object)
     */
    public boolean validateObject(Object o) {
        return true;
    }

    /**
     * @see org.apache.commons.pool.PoolableObjectFactory#activateObject(java.lang.Object)
     */
    public void activateObject(Object o) throws Exception {
    }

    /**
     * @see org.apache.commons.pool.PoolableObjectFactory#passivateObject(java.lang.Object)
     */
    public void passivateObject(Object o) throws Exception {
    }

}

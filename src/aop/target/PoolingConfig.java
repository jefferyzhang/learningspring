package aop.target;

/**
 * Config interface for a pooling invoker.
 * @author Rod Johnson
 * @version $Id: PoolingConfig.java,v 1.3 2004/03/18 02:46:13 trisberg Exp $
 */
public interface PoolingConfig {

    int getMaxSize();

    int getActive() throws UnsupportedOperationException;

    int getFree() throws UnsupportedOperationException;

}

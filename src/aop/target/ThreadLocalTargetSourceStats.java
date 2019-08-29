package aop.target;

/**
 * Statistics for a ThreadLocal TargetSource.
 * @author Rod Johnson
 * @version $Id: ThreadLocalTargetSourceStats.java,v 1.2 2004/03/18 02:46:13 trisberg Exp $
 */
public interface ThreadLocalTargetSourceStats {

    /**
     * @return all clients given one of us
     */
    int getInvocations();

    /**
     * @return hits that were satisfied by a thread bound object
     */
    int getHits();

    /**
     * @return thread bound objects created
     */
    int getObjects();

}

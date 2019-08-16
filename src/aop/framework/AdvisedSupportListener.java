package aop.framework;

/**
 *
 * @author Rod Johnson
 * @version $Id: AdvisedSupportListener.java,v 1.2 2004/03/18 02:46:05 trisberg Exp $
 */
public interface AdvisedSupportListener {

    /**
     * Invoked when first proxy is created
     * @param advisedSupport
     */
    void activated(AdvisedSupport advisedSupport);

    /**
     * Invoked when advice is changed after a proxy is created
     * @param advisedSupport
     */
    void adviceChanged(AdvisedSupport advisedSupport);

}

package aop;

import org.aopalliance.aop.Advice;


/**
 * Superinterface for all before advice. Spring supports only method before
 * advice. Although this is unlikely to change, this API is designed to
 * allow field advice in future if desired.
 * @author Rod Johnson
 * @version $Id: BeforeAdvice.java,v 1.3 2004/03/19 16:54:36 johnsonr Exp $
 */
public interface BeforeAdvice extends Advice {

}
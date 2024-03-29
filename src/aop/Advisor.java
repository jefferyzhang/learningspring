package aop;

import org.aopalliance.aop.Advice;

/**
 * Base interface holding AOP <b>advice</b> (action to take at a joinpoint)
 * and a filter determining the applicability of the advice (such as
 * a pointcut). <i>This interface is not for use by Spring users, but to
 * allow for commonality in support for different types of advice.</i>
 *
 * <p>Spring AOP is based around <b>around advice</b> delivered via method
 * <b>interception</b>, compliant with the AOP Alliance interception API.
 * The Advisor interface allows support for
 * different types of advice, such as <b>before</b> and <b>after</b> advice,
 * which need not be implemented using interception.
 *
 * @author Rod Johnson
 * @version $Id: Advisor.java,v 1.6 2004/03/19 16:54:36 johnsonr Exp $
 */
public interface Advisor {

    /**
     * Return whether this advice is associated with a particular instance
     * (for example, creating a mixin) or is it shared with all instances of
     * the advised class obtained from the same Spring bean factory.
     * <b>Note that this method is not currently used by the framework</b>.
     * Use singleton/prototype bean definitions or appropriate programmatic
     * proxy creation to ensure that Advisors have the correct lifecycle model.
     */
    boolean isPerInstance();

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a throws advice, before advice etc.
     * <br>Spring supports user-defined advice, via the org.springframework.aop.adapter
     * package.
     * @return the advice that should apply if the pointcut matches
     */
    Advice getAdvice();

}

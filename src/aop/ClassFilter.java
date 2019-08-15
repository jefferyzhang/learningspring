package aop;

/**
 * Filter that restricts matching of a pointcut or introduction to
 * a given set of target classes.
 *
 * <p>Can be used as part of a pointcut, or for the entire targeting
 * of an IntroductionAdvice.
 *
 * @author Rod Johnson
 * @version $Id: ClassFilter.java,v 1.4 2004/03/18 02:46:07 trisberg Exp $
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz candidate target class
     * @return whether the advice should apply to this candidate target class
     */
    boolean matches(Class clazz);


    /**
     * Canonical instance of a ClassFilter that matches all classes.
     */
    ClassFilter TRUE = new ClassFilter() {
        public boolean matches(Class clazz) {
            return true;
        }
    };

}

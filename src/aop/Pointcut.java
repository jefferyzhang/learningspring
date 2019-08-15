package aop;

/**
 * Core Spring pointcut abstraction. A pointcut is composed of ClassFilters and MethodMatchers.
 * Both these basic terms and a Pointcut itself can be combined to build up combinations.
 * @author Rod Johnson
 * @version $Id: Pointcut.java,v 1.3 2004/03/18 02:46:07 trisberg Exp $
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

    // could add getFieldMatcher() without breaking most existing code


    /**
     * Canonical instance that matches everything.
     */
    Pointcut TRUE = new Pointcut() {

        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        public MethodMatcher getMethodMatcher() {
            return MethodMatcher.TRUE;
        }

        public String toString() {
            return "Pointcut.TRUE";
        }
    };


}

package aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Spring implementation of AOP Alliance MethodInvocation interface.
 * Invokes target using reflection. Subclasses can override the
 * invokeJoinpoint() method to change this behaviour, so this is a useful
 * base class for MethodInvocation implementations.
 * @author Rod Johnson
 * @version $Id: ReflectiveMethodInvocation.java,v 1.4 2004/03/19 16:54:41 johnsonr Exp $
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected Method method;

    protected Object[] arguments;

    protected Object target;

    protected Object proxy;

    /**
     * List of Methodnterceptor and InterceptorAndDynamicMethodMatcher that need dynamic checks.
     **/
    protected List interceptorsAndDynamicMethodMatchers;

    /**
     * Index from 0 of the current interceptor we're invoking.
     * -1 until we invoke: then the current interceptor
     */
    private int currentInterceptorIndex = -1;

    private Class targetClass;


    /**
     * Construct a new MethodInvocation with given arguments
     * @param interceptorsAndDynamicMethodMatchers interceptors that should be applied,
     * along with any InterceptorAndDynamicMethodMatchers that need evaluation at runtime.
     * MethodMatchers included in this struct must already have been found to have matched as far
     * as was possibly statically. Passing an array might be about 10% faster, but would complicate
     * the code. And it would work only for static pointcuts.
     */
    public ReflectiveMethodInvocation(Object proxy, Object target,
                                      Method m, Object[] arguments,
                                      Class targetClass, List interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = m;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }


    /**
     * Return the method invoked on the proxied interface.
     * May or may not correspond with a method invoked on an underlying
     * implementation of that interface.
     * @return Method
     */
    public final Method getMethod() {
        return this.method;
    }

    public final AccessibleObject getStaticPart() {
        return this.method;
    }

    /**
     * Return the proxy that this interception was made through
     * @return Object
     */
    public final Object getProxy() {
        return this.proxy;
    }

    /**
     * Private optimization method
     * @return Object[]
     */
    public final Object[] getArguments() {
        return this.arguments;
    }

    /**
     * @see org.aopalliance.intercept.Invocation#proceed
     */
    public Object proceed() throws Throwable {
        //	We start with an index of -1 and increment early
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return invokeJoinpoint();
        }

        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
            // Evaluate dynamic method matcher here: static part will already have
            // been evaluated and found to match
            InterceptorAndDynamicMethodMatcher dm = (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
            if (dm.methodMatcher.matches(this.method, this.targetClass, this.arguments)) {
                return dm.interceptor.invoke(this);
            }
            else {
                // Dynamic matching failed
                // Skip this interceptor and invoke the next in the chain
                return proceed();
            }
        }
        else {
            // It's an interceptor so we just invoke it: the pointcut will have
            // been evaluated statically before this object was constructed
            return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
        }
    }

    /**
     * Invoke the joinpoint using reflection. Subclasses can override this to use custom
     * invocation.
     * @return the return value of the joinpoint
     * @throws Throwable if invoking the joinpoint resulted in an exception
     */
    protected Object invokeJoinpoint() throws Throwable {
        return AopProxyUtils.invokeJoinpointUsingReflection(target, method, arguments);
    }


    /**
     * @see org.aopalliance.intercept.Invocation#getThis
     */
    public final Object getThis() {
        return this.target;
    }

    public String toString() {
        // Don't do toString on target, it may be
        // proxied

        // ToString on args may also fail
        String s =  "Invocation: method=[" + method + "] " +
                //"args=[" + StringUtils.arrayToDelimitedString(arguments, ",") +
                "args=" + this.arguments +
                "] ";

        s += (this.target == null) ? "target is null":
                "target is of class " + target.getClass().getName();
        return s;

    }

}

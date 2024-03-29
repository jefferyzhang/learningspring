package aop.support;

import aop.IntroductionInterceptor;
import aop.framework.support.AopUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Convenient implementation of the IntroductionInterceptor interface.
 * <br/>Subclasses merely need to extend this class and
 * implement the interfaces to be introduced themselves.
 * In this case the delegate is the subclass instance itself.
 * Alternatively a separate delegate may implement the interface,
 * and be set via the delegate bean property.
 * Delegates or subclasses may implement any number of interfaces.
 * All interfaces except IntroductionInterceptor are picked up
 * from the subclass or delegate by default.<br>
 * The suppressInterface() method can be used to suppress interfaces implemented
 * by the delegate but which should not be introduced to the owning
 * AOP proxy.
 * @author Rod Johnson
 * @version $Id: DelegatingIntroductionInterceptor.java,v 1.2 2004/03/18 02:46:11 trisberg Exp $
 */
public class DelegatingIntroductionInterceptor implements IntroductionInterceptor {

    protected final Log logger = LogFactory.getLog(getClass());

    /** Set of Class */
    private Set publishedInterfaces = new HashSet();

    /**
     * Object that actually implements the interfaces.
     * May be "this" if a subclass implements the introduced interfaces.
     */
    private Object delegate;


    /**
     * Construct a new DelegatingIntroductionInterceptor, providing
     * a delegate that implements the interfaces to be introduced.
     * @param delegate the delegate that implements the introduced interfaces
     */
    public DelegatingIntroductionInterceptor(Object delegate) {
        init(delegate);
    }

    /**
     * Construct a new DelegatingIntroductionInterceptor.
     * The delegate will be the subclass, which must implement
     * additional interfaces.
     */
    protected DelegatingIntroductionInterceptor() {
        init(this);
    }

    /**
     * Both constructors use this, as it's impossible to pass
     * "this" from one constructor to another.
     */
    private void init(Object delegate) {
        if (delegate == null)
            throw new IllegalArgumentException("Delegate cannot be null in DelegatingIntroductionInterceptor");
        this.delegate = delegate;
        this.publishedInterfaces.addAll(AopUtils.getAllInterfacesAsList(delegate));
        // We don't want to expose the control interface
        suppressInterface(IntroductionInterceptor.class);
    }


    /**
     * Suppress the specified interface, which will have
     * been autodetected due to its implementation by
     * the delegate.
     * Does nothing if it's not implemented by the delegate
     * @param intf interface to suppress
     */
    public void suppressInterface(Class intf) {
        this.publishedInterfaces.remove(intf);
    }

    public Class[] getIntroducedInterfaces() {
        return (Class[]) this.publishedInterfaces.toArray(new Class[this.publishedInterfaces.size()]);
    }


    public boolean implementsInterface(Class intf) {
        return this.publishedInterfaces.contains(intf);
    }

    /**
     * Subclasses may need to override this if they want to  perform custom
     * behaviour in around advice. However, subclasses should invoke this
     * method, which handles introduced interfaces and forwarding to the target.
     */
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (isMethodOnIntroducedInterface(mi)) {
            if (logger.isDebugEnabled())
                logger.debug("invoking self on invocation [" + mi + "]; breaking interceptor chain");
            return mi.getMethod().invoke(this.delegate, mi.getArguments());
        }

        // If we get here, just pass the invocation on
        return mi.proceed();
    }

    /**
     * Is this method on an introduced interface?
     * @param mi method invocation
     * @return whether the method is on an introduced interface
     */
    protected final boolean isMethodOnIntroducedInterface(MethodInvocation mi) {
        return this.publishedInterfaces.contains(mi.getMethod().getDeclaringClass());
    }

}

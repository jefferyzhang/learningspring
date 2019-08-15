package aop;

/**
 * A TargetSource is used to obtain the current "target" of
 * an AOP invocation, which will be invoked via reflection if no
 * around advice chooses to end the interceptor chain itself.
 * <br>If a TargetSource is "static", it will always
 * return the same target, allowing optimizations in the AOP framework.
 * Dynamic target sources can support pooling, hot swapping etc.
 * <br>Application developers don't usually need to work with TargetSources
 * directly: this is an AOP framework interface.
 * @author Rod Johnson
 * @version $Id: TargetSource.java,v 1.4 2004/03/18 02:46:07 trisberg Exp $
 */
public interface TargetSource {

    Class getTargetClass();

    boolean isStatic();

    Object getTarget() throws Exception;

    void releaseTarget(Object target) throws Exception;

}

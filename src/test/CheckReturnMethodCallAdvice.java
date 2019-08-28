package test;

import aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 19 14:20
 */
public class CheckReturnMethodCallAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable {
        System.out.println("return :"+returnValue);
    }
}

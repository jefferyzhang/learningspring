package test;

import aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 19 11:54
 */
public class LogMethodCallAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method m, Object[] args, Object target) throws Throwable {
        System.out.println("calling :"+m.getName());
    }
}

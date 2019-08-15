package test;

import util.StopWatch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 14 10:59
 */
public class PerfProxy implements InvocationHandler {

    private Object target;

    public <T> T bind(Class<T> interfaceClazz, Object target){
        this.target = target;
        Class[] interfaces = new Class[1];
        interfaces[0]= interfaceClazz;

        return (T)Proxy.newProxyInstance(interfaceClazz.getClassLoader(),interfaces,this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("toString")){
          return method.invoke(target,args);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("invoke method:"+method.getName());

        Object result = method.invoke(target, args);

        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        return result;
    }
}

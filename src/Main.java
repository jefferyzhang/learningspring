import aop.Pointcut;
import aop.framework.*;
import aop.framework.adapter.AfterReturningAdviceInterceptor;
import aop.support.DefaultPointcutAdvisor;
import aop.target.SingletonTargetSource;
import beans.MutablePropertyValues;
import beans.factory.support.ManagedList;
import beans.factory.support.RuntimeBeanReference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.*;
import test.invokechain.*;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    private static Log logger = LogFactory.getLog("main");

    public static void main(String[] args)  {


    }

    private static void proxyFactoryTest() {
        ProxyFactory factory = new ProxyFactory(new Class[]{IPerson.class});

        factory.setTargetSource(new SingletonTargetSource(new Person(){{setName("jf");}}));


        System.out.println(factory.getTargetSource());
        System.out.println(factory.getProxiedInterfaces()[0]);


        factory.addInterceptor(new AfterReturningAdviceInterceptor(new CheckReturnMethodCallAdvice()));
        factory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new LogMethodCallAdvice()));

        System.out.println(factory.getAdvisors()[0]);


        IPerson personProxy = (IPerson) factory.getProxy();

        personProxy.sayHi();
    }

    private static void invokechaintest() {
        PX px = new PX();
        AdminInvoker adminInvoker = new AdminInvoker();
        BossInvoker bossInvoker = new BossInvoker();
        InvokeProcessChain invokeProcessChain = new InvokeProcessChain(Arrays.asList(bossInvoker,adminInvoker), px);
        invokeProcessChain.process();
    }

    private static void testSimpleBeanFactory() {
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();

        beanFactory.register("baby", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "babyboy");
                    put("age", "3");
                }})
                , true);

        ManagedList children = new ManagedList();
        children.add(new RuntimeBeanReference("baby"));

        beanFactory.register("partnerwife", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "wife");
                    put("age", "30");
                }}), true);


        PerfFactoryBean.proxyConfig(IPerson.class);
        beanFactory.register("partnerwifeproxy", PerfFactoryBean.class
                , new MutablePropertyValues(new HashMap() {{
                    put("target", new RuntimeBeanReference("partnerwife"));
                }}), true);

        beanFactory.register("jeffery", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "jeffery");
                    put("age", "31");
                    put("partner", new RuntimeBeanReference("partnerwifeproxy"));
                    put("children", children);
                }})
                , true);

        Person jeffery = (Person) beanFactory.getBean("jeffery");

        System.out.println(jeffery);
        System.out.println(jeffery.getPartner());
        System.out.println(jeffery.getChildren().get(0));
        System.out.println(jeffery.getPartner().sayHi());
    }

}




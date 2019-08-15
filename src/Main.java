import beans.MutablePropertyValues;
import beans.factory.support.ManagedList;
import beans.factory.support.RuntimeBeanReference;
import test.IPerson;
import test.PerfFactoryBean;
import test.Person;
import test.SimpleBeanFactory;

import java.beans.*;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

    private static Log logger = LogFactory.getLog("main");

    public static void main(String[] args) throws PropertyVetoException, IntrospectionException {
        testSimpleBeanFactory();
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




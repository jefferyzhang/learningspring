import beans.MutablePropertyValues;
import beans.factory.support.ManagedList;
import beans.factory.support.RuntimeBeanReference;
import test.Person;
import test.SimpleBeanFactory;

import java.beans.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws PropertyVetoException, IntrospectionException {

        SimpleBeanFactory beanFactory = new SimpleBeanFactory();

        beanFactory.register("partnerwife", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "wife");
                    put("age", "30");
                }})
                , true);

        beanFactory.register("baby", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "babyboy");
                    put("age", "3");
                }})
                , true);

        ManagedList managedList = new ManagedList();
        managedList.add(new RuntimeBeanReference("baby"));

        beanFactory.register("jeffery", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "jeffery");
                    put("age", "31");
                    put("partner",new RuntimeBeanReference("partnerwife"));
                    put("children",managedList);
                }})
                , true);

        Person jeffery = (Person)beanFactory.getBean("jeffery");
        System.out.println(jeffery.getPartner());
        System.out.println(jeffery.getChildren().get(0));
    }
}




import beans.MutablePropertyValues;
import test.Person;
import test.SimpleBeanFactory;

import java.beans.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws PropertyVetoException, IntrospectionException {

        SimpleBeanFactory beanFactory = new SimpleBeanFactory();

        beanFactory.register("jeffery", Person.class
                , new MutablePropertyValues(new HashMap() {{
                    put("name", "jeffery");
                    put("age", "3");
                }})
                , true);

        Object jeffery = beanFactory.getBean("jeffery");
        System.out.println(jeffery);
    }
}




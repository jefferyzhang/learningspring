import beans.BeanWrapperImpl;

import java.beans.PropertyVetoException;

public class Main {

    public static void main(String[] args) throws PropertyVetoException {
        Person person = new Person();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(person);

        beanWrapper.setPropertyValue("name","jeffery");
        beanWrapper.setPropertyValue("age",3);

        System.out.println(person.toString());
    }


}


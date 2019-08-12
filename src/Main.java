import beans.BeanWrapperImpl;

import java.beans.*;

public class Main {

    public static void main(String[] args) throws PropertyVetoException, IntrospectionException {
        Person person = new Person();



        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(person);

        beanWrapper.setPropertyValue("name","jeffery");
        beanWrapper.setPropertyValue("age","3");

        System.out.println(person.toString());

        beanWrapper.setPropertyValue("partner",new Person());
        beanWrapper.setPropertyValue("partner.name","jeffery's wife");
        System.out.println(person.getPartner().toString());

        beanWrapper.setPropertyValue("scores","1,2,3");
        System.out.println(person.getScores().toString());


        beanWrapper.setPropertyValue("girl","true");
        System.out.println(person.isGirl());

        beanWrapper.setPropertyValue("pvs","id=1\r\namount=1000");
    }


}


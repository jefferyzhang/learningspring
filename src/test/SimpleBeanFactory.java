package test;

import beans.PropertyValues;
import beans.factory.NoSuchBeanDefinitionException;
import beans.factory.support.AbstractBeanDefinition;
import beans.factory.support.AbstractBeanFactory;
import beans.factory.support.RootBeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 13 16:06
 */
public class SimpleBeanFactory extends AbstractBeanFactory {

    private Map<String,AbstractBeanDefinition> beanDefinitions= new HashMap<>();

    @Override
    protected AbstractBeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        if(beanDefinitions.containsKey(beanName)==false){
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return beanDefinitions.get(beanName);
    }

    public <T> void register(String beanName, Class<T> clazz, PropertyValues pvs, boolean singleton){
        RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz, pvs, singleton);
        beanDefinitions.put(beanName,beanDefinition);
    }
}

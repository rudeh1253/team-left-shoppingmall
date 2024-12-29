package team.left.framework.context.core.mapper;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class BeanInfoWrapper {
    private final Map<String, BeanInstantiatable> beanInitializerByBeanName;
    private final Map<String, Class<?>> typePerBean;
    private final Map<String, Method> methodInstantiatedBeans;
    
    public BeanInfoWrapper(Map<String, BeanInstantiatable> beanInitializerByBeanName,
            Map<String, Class<?>> typePerBean, Map<String, Method> methodInstantiatedBeans) {
        this.beanInitializerByBeanName = Collections.unmodifiableMap(beanInitializerByBeanName);
        this.typePerBean = Collections.unmodifiableMap(typePerBean);
        this.methodInstantiatedBeans = Collections.unmodifiableMap(methodInstantiatedBeans);
    }

    public Map<String, BeanInstantiatable> getBeanInitializerByBeanName() {
        return beanInitializerByBeanName;
    }

    public Map<String, Class<?>> getTypePerBean() {
        return typePerBean;
    }
    
    public BeanInstantiatable getInstantiatorByBeanName(String beanName) {
        return this.beanInitializerByBeanName.get(beanName);
    }
    
    public boolean isInstantiatedByMethod(String beanName) {
        return this.methodInstantiatedBeans.containsKey(beanName);
    }
    
    public Method getMethodToInstantiate(String beanName) {
        return this.methodInstantiatedBeans.get(beanName);
    }
}

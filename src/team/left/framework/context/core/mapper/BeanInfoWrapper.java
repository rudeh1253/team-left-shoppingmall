package team.left.framework.context.core.mapper;

import java.util.Collections;
import java.util.Map;

public class BeanInfoWrapper {
    private final Map<String, BeanInstantiatable> beanInitializerByBeanName;
    private final Map<String, Class<?>> typePerBean;
    
    public BeanInfoWrapper(Map<String, BeanInstantiatable> beanInitializerByBeanName,
            Map<String, Class<?>> typePerBean) {
        this.beanInitializerByBeanName = Collections.unmodifiableMap(beanInitializerByBeanName);
        this.typePerBean = Collections.unmodifiableMap(typePerBean);
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
}

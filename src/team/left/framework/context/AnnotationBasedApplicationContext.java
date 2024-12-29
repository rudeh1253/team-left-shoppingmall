package team.left.framework.context;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import team.left.framework.context.exception.AmbiguousBeanException;
import team.left.framework.context.exception.NoBeanOfTypeException;

public class AnnotationBasedApplicationContext implements ApplicationContext {
    private final Map<String, Object> instanceByBeanName;
    private final Map<Class<?>, Collection<String>> beanNamesByType;
    private final Map<Class<? extends Annotation>, Collection<String>> beanNamesByAnnotation;

    AnnotationBasedApplicationContext(Map<String, Object> instanceByBeanName,
                                      Map<Class<?>, Collection<String>> beanNamesByType,
                                      Map<Class<? extends Annotation>, Collection<String>> beanNamesByAnnotation) {
        this.instanceByBeanName = instanceByBeanName;
        this.beanNamesByType = beanNamesByType;
        this.beanNamesByAnnotation = beanNamesByAnnotation;
    }

    @Override
    public Object getBean(String name) {
        return instanceByBeanName.get(name);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        Collection<String> beanNames = beanNamesByType.get(type);
        if (beanNames.size() > 1) {
            throw new AmbiguousBeanException("Bean이 2개 이상 존재합니다: " + type);
        }
        if (beanNames == null || beanNames.isEmpty()) {
            throw new NoBeanOfTypeException("해당 타입의 Bean이 없습니다: " + type);
        }
        return (T)instanceByBeanName.get(beanNames.iterator().next());
    }

    @Override
    public <T> T getBean(String name, Class<T> type) {
        return (T)instanceByBeanName.get(name);
    }

    @Override
    public <T> List<T> getBeansOfType(Class<T> type) {
        Collection<String> beanNames = beanNamesByType.get(type);
        return beanNames.stream()
                .map((name) -> (T)this.instanceByBeanName.get(name))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        if (!this.beanNamesByAnnotation.containsKey(annotationType)) {
            return new HashMap<>();
        }
        return this.beanNamesByAnnotation.get(annotationType)
                .stream()
                .collect(Collectors.toMap(
                        (name) -> name,
                        this.instanceByBeanName::get
                ));
    }
}

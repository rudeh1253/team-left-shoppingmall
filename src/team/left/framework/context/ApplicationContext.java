package team.left.framework.context;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public interface ApplicationContext {
    
    Object getBean(String name);

    <T> T getBean(Class<T> type);

    <T> T getBean(String name, Class<T> type);

    <T> List<T> getBeansOfType(Class<T> type);

    Map<String, Object> getBeansWithAnnotation(Class <? extends Annotation> annotationType);
}

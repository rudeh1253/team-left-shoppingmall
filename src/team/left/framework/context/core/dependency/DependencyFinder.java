package team.left.framework.context.core.dependency;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public interface DependencyFinder {

    Constructor<?> getInjectionConstructor(Class<?> beanClass);

    Collection<Field> getInjectionFields(Class<?> beanClass);

    Collection<Method> getInjectionMethods(Class<?> beanClass);
}

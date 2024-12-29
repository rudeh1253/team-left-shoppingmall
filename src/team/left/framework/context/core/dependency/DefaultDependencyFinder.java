package team.left.framework.context.core.dependency;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import team.left.framework.context.annotation.Autowired;
import team.left.framework.context.exception.AmbiguousConstructorException;
import team.left.framework.context.exception.NoPublicConstructorException;

public class DefaultDependencyFinder implements DependencyFinder {

    @Override
    public Constructor<?> getInjectionConstructor(Class<?> beanClass) {
        Constructor<?>[] publicConstructors =
                Arrays.stream(beanClass.getDeclaredConstructors()).filter((c) -> Modifier.isPublic(c.getModifiers())).toArray(Constructor[]::new);
        if (publicConstructors.length == 0) {
            throw new NoPublicConstructorException("Public 생성자가 없습니다: " + beanClass);
        }

        if (publicConstructors.length == 1) {
            return publicConstructors[0];
        }

        Constructor<?>[] annotatedConstructors = Arrays.stream(publicConstructors)
                .filter((c) -> c.isAnnotationPresent(Autowired.class))
                .toArray(Constructor[]::new);

        if (annotatedConstructors.length != 1) {
            throw new AmbiguousConstructorException("생성자 주입 대상이 모호합니다: " + beanClass);
        }
        return annotatedConstructors[0];
    }

    @Override
    public Collection<Field> getInjectionFields(Class<?> beanClass) {
        return Arrays.stream(beanClass.getDeclaredFields())
                .filter((c) -> c.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Method> getInjectionMethods(Class<?> beanClass) {
        return Arrays.stream(beanClass.getDeclaredMethods())
                .filter((m) -> m.getName().startsWith("set"))
                .filter((m) -> m.getParameterCount() == 1)
                .filter((m) -> m.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toList());
    }
}

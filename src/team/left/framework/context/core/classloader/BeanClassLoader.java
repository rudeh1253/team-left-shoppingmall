package team.left.framework.context.core.classloader;

import java.util.Set;

import team.left.framework.context.annotation.Component;
import team.left.framework.context.exception.BeanLoaderException;

/**
 * Container에서 관리할 객체들의 클래스 정보를 탐색하는 객체
 * Component 어노테이션이 붙은 클래스들을 탐색한다.
 * 
 * @see Component
 */
public interface BeanClassLoader {
    
    /**
     * basePackage로부터 Context에 포함될 모든 클래스들을 클래스를 탐색한다.
     * basePackage와 그 하위 패키지에 대해 탐색을 진행하며, basePackage의 부모 패키지는 탐색하지 않는다.
     * 
     * @param basePackage 클래스를 탐색의 시작점이 되는 패키지.
     * @return basePackage 이하 모든 클래스 정보.
     * @throws BeanLoaderException Context에 포함될 Bean을 로드할 때 문제가 발생할 경우
     */
    Set<Class<?>> findAllOnBasePackage(String basePackage) throws BeanLoaderException;
}

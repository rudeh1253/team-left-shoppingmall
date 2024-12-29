package team.left.framework.web.resolver.result;

import java.util.Map;

public interface ResultResolver {

    <T> String resolveViewName(T result);
    
    <T> Map<String, Object> resolveAttributes(T result);

    boolean supports(Class<?> resultTarget);
}

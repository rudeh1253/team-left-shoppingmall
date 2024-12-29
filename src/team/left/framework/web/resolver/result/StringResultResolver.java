package team.left.framework.web.resolver.result;

import java.util.Collections;
import java.util.Map;

public class StringResultResolver implements ResultResolver {

    @Override
    public <T> String resolveViewName(T result) {
        return (String) result;
    }

    @Override
    public <T> Map<String, Object> resolveAttributes(T result) {
        return Collections.emptyMap();
    }

    @Override
    public boolean supports(Class<?> resultTarget) {
        return String.class.isAssignableFrom(resultTarget);
    }
}

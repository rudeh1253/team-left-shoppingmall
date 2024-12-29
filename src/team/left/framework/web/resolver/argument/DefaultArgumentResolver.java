package team.left.framework.web.resolver.argument;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultArgumentResolver implements ArgumentResolver {

    @Override
    public Object[] resolveArguments(Method targetMethod, HttpServletRequest request, HttpServletResponse response) {
        return new Object[] {
                request, response
        };
    }
}

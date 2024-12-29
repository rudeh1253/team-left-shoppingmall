package team.left.framework.web.resolver.argument;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ArgumentResolver {

    Object[] resolveArguments(Method targetMethod, HttpServletRequest request, HttpServletResponse response);
}

package team.left.framework.web.resolver.argument;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.model.Model;
import team.left.framework.web.resolver.argument.util.AppropriateParameterMatcher;

public class ModelArgumentResolver {

    public Object[] resolveArguments(Method targetMethod, HttpServletRequest request, HttpServletResponse response, Model model) {
        Parameter[] parameters = targetMethod.getParameters();
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (Model.class.isAssignableFrom(parameter.getType())) {
                arguments[i] = model;
            } else {
                arguments[i] = AppropriateParameterMatcher.matchParameter(parameter, request, response);
            }
        }
        return arguments;
    }
}

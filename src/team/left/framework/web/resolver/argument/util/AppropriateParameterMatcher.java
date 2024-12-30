package team.left.framework.web.resolver.argument.util;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.parameter.RequestParam;
import team.left.framework.web.parameter.RequestParamContainer;

public class AppropriateParameterMatcher {

    public static Object matchParameter(Parameter parameter, HttpServletRequest request, HttpServletResponse response) {
        if (parameter.isAnnotationPresent(RequestParamContainer.class)) {
            return bindToObject(request, parameter);
        } else if (parameter.isAnnotationPresent(RequestParam.class)) {
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            return request.getParameter(requestParam.value());
        } else if (HttpSession.class.isAssignableFrom(parameter.getType())) {
            return request.getSession();
        } else if (ServletContext.class.isAssignableFrom(parameter.getType())) {
            return request.getServletContext();
        } else if (HttpServletRequest.class.isAssignableFrom(parameter.getType())) {
            return request;
        } else if (HttpServletResponse.class.isAssignableFrom(parameter.getType())) {
            return response;
        }
        throw new RuntimeException();
    }
    
    private static Object bindToObject(HttpServletRequest request, Parameter target) {
        try {
            Class<?> type = target.getType();
            Object toBind = type.getConstructor().newInstance();
            
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                
                String fieldName = field.getName();
                Object requestParam = request.getParameter(fieldName);
                field.set(toBind, requestParam);
                
                field.setAccessible(false);
            }
            return toBind;
        } catch (Exception e) {
            throw new IllegalArgumentException(e); // TODO: 구체적인 예외
        }
    }
}

package team.left.framework.web.adapter.delegator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.adapter.ActionAdapter;
import team.left.framework.web.model.Model;
import team.left.framework.web.resolver.argument.ModelArgumentResolver;
import team.left.framework.web.resolver.handler.HandlerSet;

public class ModelActionAdapter implements ActionAdapter {
    private final ModelArgumentResolver argumentResolver = new ModelArgumentResolver();

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, HandlerSet handlerSet) {
        Model model = new Model();
        Object actionInstance = handlerSet.getHandlerInstance();
        Method handlerMethod = handlerSet.getHandlerMethod();
        
        try {
            String viewName = (String) handlerMethod.invoke(actionInstance, this.argumentResolver.resolveArguments(handlerMethod, request, response, model));
            model.getAttributes().forEach((k, v) -> request.setAttribute(k, v));
            return viewName;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(HandlerSet handlerSet) {
        Method method = handlerSet.getHandlerMethod();
        return Arrays.stream(method.getParameterTypes()).anyMatch((clazz) -> Model.class.isAssignableFrom(clazz));
    }
}

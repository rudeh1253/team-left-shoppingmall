package team.left.framework.web.adapter.delegator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.adapter.ActionAdapter;
import team.left.framework.web.model.ModelAndView;
import team.left.framework.web.resolver.argument.ArgumentResolver;
import team.left.framework.web.resolver.argument.DefaultArgumentResolver;
import team.left.framework.web.resolver.handler.HandlerSet;

public class ModelAndViewActionAdapter implements ActionAdapter {
    private final ArgumentResolver argumentResolver = new DefaultArgumentResolver();

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, HandlerSet handlerSet) {
        Object actionInstance = handlerSet.getHandlerInstance();
        Method handlerMethod = handlerSet.getHandlerMethod();
        
        try {
            ModelAndView mv = (ModelAndView) handlerMethod.invoke(actionInstance, this.argumentResolver.resolveArguments(handlerMethod, request, response));
            mv.getAttributes().forEach((k, v) -> request.setAttribute(k, v));
            return mv.getViewName();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(HandlerSet handlerSet) {
        return ModelAndView.class.isAssignableFrom(handlerSet.getHandlerMethod().getReturnType());
    }
}

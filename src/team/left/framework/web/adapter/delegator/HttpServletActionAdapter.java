package team.left.framework.web.adapter.delegator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.adapter.ActionAdapter;
import team.left.framework.web.resolver.handler.HandlerSet;

public class HttpServletActionAdapter implements ActionAdapter {

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, HandlerSet handlerSet) {
        Object actionInstance = handlerSet.getHandlerInstance();
        Method handlerMethod = handlerSet.getHandlerMethod();
        
        // TODO
        try {
            return (String) handlerMethod.invoke(actionInstance, request, response);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(HandlerSet handlerSet) {
        return true;
    }
}

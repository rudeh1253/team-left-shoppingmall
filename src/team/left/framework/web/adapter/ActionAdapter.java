package team.left.framework.web.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.resolver.handler.HandlerSet;

public interface ActionAdapter {

    String handle(HttpServletRequest request, HttpServletResponse response, HandlerSet handlerSet);
    
    boolean supports(HandlerSet handlerSet);
}

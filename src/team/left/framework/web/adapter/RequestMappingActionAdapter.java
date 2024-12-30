package team.left.framework.web.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.adapter.delegator.HttpServletActionAdapter;
import team.left.framework.web.adapter.delegator.ModelActionAdapter;
import team.left.framework.web.adapter.delegator.ModelAndViewActionAdapter;
import team.left.framework.web.resolver.handler.HandlerSet;

public class RequestMappingActionAdapter {
    
    private final ActionAdapter[] actionAdapters = {
            new ModelAndViewActionAdapter(), new ModelActionAdapter(), new HttpServletActionAdapter()
    };

    public String handle(HttpServletRequest request, HttpServletResponse response, HandlerSet handlerSet) {
        for (ActionAdapter actionAdapter : this.actionAdapters) {
            if (actionAdapter.supports(handlerSet)) {
                return actionAdapter.handle(request, response, handlerSet);
            }
        }
        throw new RuntimeException("Something Wrong");
    }
}

package team.left.framework.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.context.AnnotationBasedApplicationContextFactory;
import team.left.framework.context.ApplicationContext;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.adapter.RequestMappingActionAdapter;
import team.left.framework.web.constant.CommonConstants;
import team.left.framework.web.exception.ActionNotFoundException;
import team.left.framework.web.resolver.handler.HandlerSet;
import team.left.framework.web.resolver.handler.RequestMappingActionMapping;
import team.left.framework.web.view.ViewResolver;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ApplicationContext applicationContext;
    private RequestMappingActionMapping actionMapping;
    private RequestMappingActionAdapter actionAdapter;
    private ViewResolver viewResolver;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String configClassName = servletConfig.getInitParameter("configClass");
        System.out.println("Hello!");
        try {
            this.applicationContext = AnnotationBasedApplicationContextFactory.getApplicationContext(Class.forName(configClassName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        this.actionMapping = this.applicationContext.getBean(RequestMappingActionMapping.class);
        this.actionMapping.init(this.applicationContext);
        
        this.actionAdapter = this.applicationContext.getBean(RequestMappingActionAdapter.class);
        
        this.viewResolver = this.applicationContext.getBean(ViewResolver.class);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        if (contextPath != null && contextPath.length() > 0) {
            uri = uri.substring(contextPath.length());
        }
        RequestMethod method = RequestMethod.valueOf(request.getMethod().trim().toUpperCase());
        String command = request.getParameter(CommonConstants.COMMAND_PAPRAM_NAME);
        
        HandlerSet handlerSet = this.actionMapping.getHandler(uri, method, command);
        if (handlerSet == null) {
            System.out.println("No handler for:");
            System.out.println("uri=" + uri);
            System.out.println("method=" + method);
            System.out.println("command=" + command);
            
            throw new ActionNotFoundException();
        }
        System.out.println("handlerSet=" + handlerSet);
        
        String viewName = this.actionAdapter.handle(request, response, handlerSet);
        if (this.viewResolver.isRedirect(viewName)) {
            response.sendRedirect(contextPath + viewName.substring(CommonConstants.REDIRECT_PREFIX.length()));
            return;
        }
        
        viewName = this.viewResolver.resolve(viewName);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
        requestDispatcher.forward(request, response);
    }
}

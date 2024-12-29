package team.left.framework.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.context.AnnotationBasedApplicationContextFactory;
import team.left.framework.context.ApplicationContext;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.constant.CommonConstants;
import team.left.framework.web.resolver.argument.ArgumentResolver;
import team.left.framework.web.resolver.argument.DefaultArgumentResolver;
import team.left.framework.web.resolver.handler.HandlerSet;
import team.left.framework.web.resolver.handler.RequestMappingActionMapping;
import team.left.framework.web.resolver.result.ResultResolver;
import team.left.framework.web.resolver.result.StringResultResolver;
import team.left.framework.web.resolver.result.ValidResultResolverNotExistsException;
import team.left.framework.web.view.ViewResolver;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ApplicationContext applicationContext;
    private RequestMappingActionMapping actionMapping;
    private ArgumentResolver argumentResolver = new DefaultArgumentResolver();
    private ResultResolver[] resultResolver = {
            new StringResultResolver()
    };
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
        
        System.out.println("uri=" + uri);
        System.out.println("method=" + method);
        System.out.println("command=" + command);
        
        HandlerSet handlerSet = this.actionMapping.getHandler(uri, method, command);
        System.out.println("handlerSet=" + handlerSet);
        
        Object[] arguments = this.argumentResolver.resolveArguments(handlerSet.getHandlerMethod(), request, response);
        
        Object result;
        try {
            result = handlerSet.handle(arguments);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        String viewName = null;
        Map<String, Object> attributes = null;
        for (ResultResolver rs : this.resultResolver) {
            if (rs.supports(result.getClass())) {
                viewName = rs.resolveViewName(result);
                attributes = rs.resolveAttributes(result);
            }
        }
        if (viewName == null || attributes == null) {
            throw new ValidResultResolverNotExistsException(result.getClass());
        }
        
        if (this.viewResolver.isRedirect(viewName)) {
            response.sendRedirect(viewName);
            return;
        }
        
        viewName = this.viewResolver.resolve(viewName);
        
        attributes.forEach((k, v) -> request.setAttribute(k, v));
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
        requestDispatcher.forward(request, response);
    }
}

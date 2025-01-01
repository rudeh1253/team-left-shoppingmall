package team.left.framework.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_SUFFIX = ".jsp";
    
    private Map<String, CommandHandler> handlers = new HashMap<>();
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String configFile = "/actions.properties";
        
        Properties props = new Properties();
        ServletContext servletContext = servletConfig.getServletContext();  
        try {
            props.load(servletContext.getResourceAsStream(configFile));
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        for (Map.Entry<Object, Object> pair : props.entrySet()) {
            String uri = (String) pair.getKey();
            String className = (String) pair.getValue();
            try {
                this.handlers.put(uri, (CommandHandler) Class.forName(className).getConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        this.handlers.values().forEach((h) -> h.init(servletConfig));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        
        if (contextPath != null && !contextPath.isEmpty()) {
            requestUri = requestUri.substring(contextPath.length());
        }
        
        String command = request.getParameter("command");
        String method = request.getMethod().trim().toUpperCase();
        CommandHandler handler = this.handlers.get(requestUri + "," + method.trim().toUpperCase() + "," + command);
        
        System.out.println("k=" + requestUri + "," + method.trim().toUpperCase() + "," + command);
        System.out.println("handler=" + handler);
        
        if (handler == null) {
            throw new HandlerNotFoundException();
        }
        
        String viewName = handler.handleCommand(request, response);
        
        if (viewName == null) {
            return;
        }
        
        if (viewName.startsWith("redirect:")) {
            response.sendRedirect(contextPath + viewName.substring("redirect:".length()));
            return;
        }
        
        request.getRequestDispatcher(VIEW_PREFIX + viewName + VIEW_SUFFIX)
                .forward(request, response);
    }
}

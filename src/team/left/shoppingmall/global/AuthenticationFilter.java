package team.left.shoppingmall.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private Set<CommandRequestInfo> blackList = null;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        InputStream is = context.getResourceAsStream("/authorization.properties");
        
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        this.blackList = properties.keySet().stream()
                .map((e) -> (String) e)
                .map((endpoint) -> CommandRequestInfo.of(endpoint.split(",")))
                .collect(Collectors.toSet());
        System.out.println("blackList=" + this.blackList);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        process((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY) != null
                || !this.blackList.contains(CommandRequestInfo.from(request))) {
            filterChain.doFilter(request, response);
            return;
        }
        
        response.sendRedirect("/member.do?command=login");
    }
}

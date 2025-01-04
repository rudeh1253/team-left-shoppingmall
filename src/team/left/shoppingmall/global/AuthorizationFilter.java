package team.left.shoppingmall.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.shoppingmall.member.dao.MemberDao;

public class AuthorizationFilter implements Filter {
    private Map<CommandRequestInfo, Set<String>> allowedRoles = null;
    
    private final MemberDao memberDao = MemberDao.getInstance();
    
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
        
        Map<CommandRequestInfo, Set<String>> allowedRoles = new HashMap<>();
        for (Object key : properties.keySet()) {
            String keyInString = (String) key;
            String[] parsed = keyInString.split(",");
         
            String uri = parsed[0];
            String method = parsed[1];
            String command = parsed[2];
            String role = properties.getProperty(keyInString);
            
            Set<String> roles = new HashSet<>();
            switch (role.trim().toUpperCase()) {
            case "SELL":
            case "BUY":
                roles.add(role.trim().toLowerCase());
                break;
            default:
                roles.add("sell");
                roles.add("buy");
            }
            
            allowedRoles.put(new CommandRequestInfo(uri, method, command), roles);
        }
        this.allowedRoles = Collections.unmodifiableMap(allowedRoles);
        System.out.println("allowedRoles=" + this.allowedRoles);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        process((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        CommandRequestInfo requestInfo = CommandRequestInfo.from(request);
        if (!this.allowedRoles.containsKey(requestInfo)) {
            chain.doFilter(request, response);
            return;
        }
        
        String role = (String) request.getSession().getAttribute(CommonConstants.MEMBER_ROLE_SESSION_KEY);
        
        if (this.allowedRoles.get(requestInfo).contains(role)) {
            chain.doFilter(request, response);
            return;
        }
        
        response.sendRedirect("/error.do?command=forbidden");
    }
}

package team.left.shoppingmall.global;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final Set<CommandRequestInfo> BLACK_LIST;
    private static final String GET = "GET";
    private static final String POST = "POST";
    
    static {
        Set<CommandRequestInfo> blackList = new HashSet<>();
        blackList.add(new CommandRequestInfo("/member.do", GET, "profile"));
        blackList.add(new CommandRequestInfo("/product.do", GET, "add-product"));
        BLACK_LIST = blackList;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        process((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY) != null
                || !BLACK_LIST.contains(CommandRequestInfo.from(request))) {
            filterChain.doFilter(request, response);
            return;
        }
        
        response.sendRedirect("/member.do?command=login");
    }
}

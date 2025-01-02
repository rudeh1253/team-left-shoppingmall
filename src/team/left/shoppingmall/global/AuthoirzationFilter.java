package team.left.shoppingmall.global;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.shoppingmall.member.dao.MemberDao;

public class AuthoirzationFilter implements Filter {
    private static final Map<CommandRequestInfo, Set<String>> ALLOWED_ROLE;
    private static final String GET = "GET";
    private static final String POST = "POST";
    
    static {
        Map<CommandRequestInfo, Set<String>> allowedRoleCache = new HashMap<>();
        allowedRoleCache.put(new CommandRequestInfo("/product.do", GET, "add-product"), of("sell"));
        ALLOWED_ROLE = Collections.unmodifiableMap(allowedRoleCache);
    }
    
    private static Set<String> of(String... roles) {
        Set<String> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(role);
        }
        return roleSet;
    }
    
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        process((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        CommandRequestInfo requestInfo = CommandRequestInfo.from(request);
        System.out.println(requestInfo);
        if (!ALLOWED_ROLE.containsKey(requestInfo)) {
            chain.doFilter(request, response);
            return;
        }
        Integer memberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        String role = this.memberDao.findRoleByMemberId(memberId);
        System.out.println("role=" + role);
        
        if (ALLOWED_ROLE.get(requestInfo).contains(role)) {
            System.out.println("contains");
            chain.doFilter(request, response);
            return;
        }
        
        response.sendRedirect("/error.do?command=forbidden");
    }
}

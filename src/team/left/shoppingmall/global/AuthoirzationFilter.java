package team.left.shoppingmall.global;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import team.left.shoppingmall.member.dao.MemberDao;

public class AuthoirzationFilter implements Filter {
    private static final Map<CommandRequestInfo, String> ALLOWED_ROLE;
    
    static {
        Map<CommandRequestInfo, String> allowedRoleCache = new HashMap<>();
        
        ALLOWED_ROLE = Collections.unmodifiableMap(allowedRoleCache);
    }
    
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        process((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        if (!ALLOWED_ROLE.containsKey(CommandRequestInfo.from(request))) {
            chain.doFilter(request, response);
            return;
        }
        Integer memberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        String role = this.memberDao.findRoleByMemberId(memberId);
        
        if (ALLOWED_ROLE.get(CommandRequestInfo.from(request)).equals(role)) {
            chain.doFilter(request, response);
            return;
        }
        
        response.sendRedirect("/error.do?command=forbidden");
    }
    
    @RequiredArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    private static class AllowedRole {
        private final CommandRequestInfo commandRequestInfo;
        private final String allowedRole;
    }
}

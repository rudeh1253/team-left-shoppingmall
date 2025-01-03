package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.io.Writer;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.member.dao.MemberDto;

public class LoginMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email=" + email);
        System.out.println("password=" + password);
        
        String result;
        try {
            Integer memberId = this.memberDao.findMemberIdByEmailPassword(email, password)
                    .orElseThrow(NoSuchElementException::new);
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstants.MEMBER_SESSION_KEY, memberId);
            MemberDto memberDto = this.memberDao.findMemberByMemberId(memberId);
            session.setAttribute(CommonConstants.MEMBER_NAME_SESSION_KEY, memberDto.getMemberName());
            session.setAttribute(CommonConstants.MEMBER_PROFILE_IMG_SESSION_KEY, memberDto.getProfileImg());
            result = "{\"success\":true}";
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            result = "{\"success\":false}";
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        response.setContentType("application/json");
        try (Writer writer = response.getWriter()) {
            writer.write(result);
        }
        
        return null;
    }
}

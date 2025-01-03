package team.left.shoppingmall.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;

public class EditPasswordPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer memberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        String currentPassword = request.getParameter("currentPassword");
        
        String password = this.memberDao.findPasswordByMemberId(memberId);
        if (!password.equals(currentPassword)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().append("{\"result\":\"INVALID_CURRENT_PASSWORD\"}");
            return null;
        }
        
        String newPassword = request.getParameter("newPassword");
        this.memberDao.updatePasswordByMemberId(newPassword, memberId);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append("{\"result\":\"SUCCESS\"}");
        return null;
    }
}

package team.left.shoppingmall.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;

public class DeleteMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer memberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        String password = request.getParameter("password");
        
        if (memberId == null) {
            return "redirect:/member.do?command=login";
        }
        
        if (this.memberDao.existsByMemberIdAndPassword(memberId, password)) {
            this.memberDao.deleteByMemberId(memberId);
            request.getSession().invalidate();
            return "redirect:/";
        }
        return "member/delete-fail";
    }
}

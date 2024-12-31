package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;

public class LoginMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        try {
            Integer memberId = this.memberDao.findMemberIdByEmailPassword(email, password)
                    .orElseThrow(NoSuchElementException::new);
            request.getSession().setAttribute(CommonConstants.MEMBER_SESSION_KEY, memberId);
            return "redirect:/member.do?command=main";
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            request.setAttribute("message", "비밀번호가 맞지 않네요!");
            return "member/login-fail";
        }
    }
}

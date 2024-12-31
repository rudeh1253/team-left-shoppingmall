package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.member.dao.MemberDao;

public class LoginMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        try {
            boolean success = this.memberDao.doesEmailPasswordMatch(email, password);
            if (!success) {
                request.setAttribute("message", "아이디가 맞지 않네요!");
            }
            return success ? "redirect:/member.do?command=main" : "member/login-fail";
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            request.setAttribute("message", "비밀번호가 맞지 않네요!");
            return "member/login-fail";
        }
    }
}

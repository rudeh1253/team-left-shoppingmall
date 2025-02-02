package team.left.shoppingmall.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;

public class LoginMemberGetAction implements CommandHandler {

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY) != null) {
            return "redirect:/";
        }
        return "member/login";
    }
}

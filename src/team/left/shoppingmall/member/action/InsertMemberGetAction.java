package team.left.shoppingmall.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class InsertMemberGetAction implements CommandHandler {

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("command", "insert-member");
        return "member/member-form";
    }
}

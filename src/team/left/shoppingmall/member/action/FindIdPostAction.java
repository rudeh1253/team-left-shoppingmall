package team.left.shoppingmall.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.member.dao.MemberDao;

public class FindIdPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = (String) request.getParameter("name");
        String tel = (String) request.getParameter("tel");

        String result = "";
        try {
            request.setAttribute("exists", true);
            result = this.memberDao.findEmailByMemberNameAndTel(name, tel).orElseGet(() -> {
                request.setAttribute("exists", false);
                return "존재하지 않는 아이디입니다";
            });
            request.setAttribute("isError", false);
        } catch (Exception e) {
            result = e.getMessage();
            request.setAttribute("isError", true);
        }

        request.setAttribute("which", "이메일");
        request.setAttribute("result", result);

        return "member/fragment/find-id-pw-modal-body";
    }

}

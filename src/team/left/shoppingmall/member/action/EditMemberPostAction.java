package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.member.dao.MemberEditDto;

public class EditMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberName = request.getParameter("member-name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("month")), Integer.parseInt(request.getParameter("day-of-month")));
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");
        
        MemberEditDto dto = MemberEditDto.builder()
                .memberName(memberName)
                .email(email)
                .address(address)
                .birthDate(birthDate)
                .tel(tel)
                .gender(gender)
                .build();
        
        HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        
        this.memberDao.updateMember(memberId, dto);
        return "redirect:/member.do?command=profile";
    }
}

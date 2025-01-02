package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.EditMemberFormDataDto;
import team.left.shoppingmall.member.dao.MemberDao;

public class EditMemberGetAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("command", "edit-member");
        Integer memberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
        Optional<EditMemberFormDataDto> result = this.memberDao.findEditMemberFormDataById(memberId);
        
        if (result.isPresent()) {
            EditMemberFormDataDto content = result.get();
            
            request.setAttribute("profileImage", content.getProfileImg());
            
            String[] profileImageSplit = content.getProfileImg().split("/");
            request.setAttribute("profileImageFileName", profileImageSplit[profileImageSplit.length - 1]);
            
            String[] emailPieces = content.getEmail().split("@");
            request.setAttribute("emailAccount", emailPieces[0]);
            request.setAttribute("emailHost", emailPieces[1]);
            
            request.setAttribute("memberName", content.getMemberName());
            request.setAttribute("tel", content.getTel());
            request.setAttribute("address", content.getAddress());
            
            LocalDate birthDate = content.getBirthDate();
            request.setAttribute("birthDateExists", true);
            request.setAttribute("year", birthDate.getYear());
            request.setAttribute("month", birthDate.getMonthValue());
            request.setAttribute("dayOfMonth", birthDate.getDayOfMonth());
            
            request.setAttribute("gender", content.getGender());
            request.setAttribute("role", content.getRole());
            request.setAttribute("company", content.getCompany());
            request.setAttribute("answer", content.getAnswer());
            
            return "member/member-form";
        }
        return "redirect:/member.do?command=login";
    }
}

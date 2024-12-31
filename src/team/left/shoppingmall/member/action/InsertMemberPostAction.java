package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.member.dao.MemberCreationDto;
import team.left.shoppingmall.member.dao.MemberDao;

public class InsertMemberPostAction implements CommandHandler {
    private final MemberDao memberDao = MemberDao.getInstance();

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberCreationDto memberCreationDto = new MemberCreationDto();
        Arrays.stream(MemberCreationDto.class.getDeclaredFields()).forEach((f) -> {
            f.setAccessible(true);
            try {
                if (f.getName().equals("birthDate")) {
                    f.set(memberCreationDto, LocalDate.of(Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("month")), Integer.parseInt(request.getParameter("dayOfMonth"))));
                } else {
                    f.set(memberCreationDto, request.getParameter(f.getName()));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            f.setAccessible(false);
        });
        
        int key = this.memberDao.insertMember(memberCreationDto);
        System.out.println("key=" + key);
        
        return "redirect:/member.do?command=login";
    }
}

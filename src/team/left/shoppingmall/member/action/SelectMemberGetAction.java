package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.SelectMemberDao;
import team.left.shoppingmall.member.dao.SelectMemberDto;

public class SelectMemberGetAction implements CommandHandler{

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SelectMemberDto member = new SelectMemberDto();
		SelectMemberDao dao = new SelectMemberDao();
		
		int id = 0;
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		if( userid == 0 ) {
			id = userid;
		} else {
			id = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		}
		if(id == 0) {
			return "member/login";
		}
		System.out.println(id);
		try {
			dao.selectMember(member, id);
		} catch (SQLException e) {
			e.getMessage();
		}
		
		
		request.setAttribute("member", member);
		
		
		 return "member/profile";
	}

}

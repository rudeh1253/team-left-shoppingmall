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

public class SelectMemberGetAction implements CommandHandler {

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SelectMemberDto member = new SelectMemberDto();
		SelectMemberDao dao = new SelectMemberDao();

		int id = 0;

		if (request.getParameter("userid") != null) {
			id = Integer.parseInt(request.getParameter("userid"));
		} else if (request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY) != null) {
			id = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		} else {
			return "member/login";
		} 
		

		try {
			dao.selectMember(member, id);
		} catch (SQLException e) {
			e.getMessage();
		}

		request.setAttribute("member", member);
		request.setAttribute("userid", id);

		return "member/profile";
	}

}

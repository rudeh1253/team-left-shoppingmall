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

		
		SelectMemberDao dao = new SelectMemberDao();
		Integer loginMemberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		String memberIdFromParameter = request.getParameter("userid");
		
		boolean isMyProfile = memberIdFromParameter == null || loginMemberId.equals(Integer.parseInt(memberIdFromParameter));
		Integer memberIdToSelect = memberIdFromParameter != null ? Integer.parseInt(memberIdFromParameter) : loginMemberId;

		SelectMemberDto member = new SelectMemberDto();
		try {
			dao.selectMember(member, memberIdToSelect);
		} catch (SQLException e) {
			e.getMessage();
		}

		request.setAttribute("member", member);
		request.setAttribute("userid", memberIdToSelect);
		request.setAttribute("isMyProfile", isMyProfile);
		request.setAttribute("role", member.getRole());
		return "member/profile";
	}

}

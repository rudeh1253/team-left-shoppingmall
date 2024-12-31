package team.left.shoppingmall.member.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.member.dao.FindPasswordDao;

public class FindPasswordPostAction implements CommandHandler{

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			FindPasswordDao dao = new FindPasswordDao();
			String email = (String) request.getParameter("email");
			String tel = (String) request.getParameter("tel");
			
			String result = "";
			try {
				result = dao.findPassword(email, tel);
			} catch (SQLException e) {
				result = e.getMessage();
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("email", result);
			
		
			return "redirect:/member.do?command=login";

	}

}

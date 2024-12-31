package team.left.shoppingmall.purchase.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class PurchaseResultAction implements CommandHandler{

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// command로 success/fail 둘 중 하나가 온다.
		String command = request.getParameter("command");
		if("success".equals(command)) {
			request.setAttribute("isSuccess", "success");
		}else {
			request.setAttribute("isSuccess", "fail");
		}
		return "cart";
	}

}

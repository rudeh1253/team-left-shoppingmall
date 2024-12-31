package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class InsertProductGetAction implements CommandHandler {

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("command", request.getParameter("command"));
		return "product/productForm";
	}

}

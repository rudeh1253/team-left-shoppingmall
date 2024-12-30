package team.left.shoppingmall.cart.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class CartAction implements CommandHandler{

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response, String method, String command)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "jun/cart";
	}
}

package team.left.shoppingmall.cart.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class CartAction implements CommandHandler {

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기 오는거임?");
		return "cart.do?command=list";
	}

}

package team.left.shoppingmall.cart.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;

public class AddCartAction implements CommandHandler{

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if("GET".equals(method)) {
			return "product/product-detail";
		}else {
			return "redirect:/cart.do?command=show-cart";
		}
	}

}


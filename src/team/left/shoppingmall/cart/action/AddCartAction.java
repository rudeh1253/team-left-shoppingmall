package team.left.shoppingmall.cart.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartDto;

public class AddCartAction implements CommandHandler{

	private CartDao dao = new CartDao();
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if("GET".equals(method)) {
			return "product/product-detail";
		}else {
			request.setCharacterEncoding("utf-8");
			int member_id = Integer.parseInt(request.getParameter("member_id"));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			CartDto cart = new CartDto(member_id, product_id, amount);
			dao.addCart(cart);
			
			//~~.do
			return "redirect:/cart.do?command=main";
		}
	}

}


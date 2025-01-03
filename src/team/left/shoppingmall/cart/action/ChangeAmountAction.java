package team.left.shoppingmall.cart.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;

public class ChangeAmountAction implements CommandHandler{

	CartDao dao = new CartDao();
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		String productId = request.getParameter("productid");
		int updatedAmount = 0;
		System.out.println(command);
		if("increase".equals(command)) {
			updatedAmount = dao.increaseCartAmount(Integer.parseInt(productId));
		}else if("decrease".equals(command)) {
			updatedAmount = dao.decreaseCartAmount(Integer.parseInt(productId));
		}else if ("delete".equals(command)) {
			System.out.println(productId);
            boolean success = dao.deleteProductFromCart(Integer.parseInt(productId));
		}
		
		System.out.println("success");
		return "cart/cart-list";
	}

}

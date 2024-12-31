package team.left.shoppingmall.purchase.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartProductDto;

public class TestAction implements CommandHandler {

	CartDao dao = new CartDao();
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String[] idArray = request.getParameterValues("productId");
			String[] amountArray = request.getParameterValues("amount");
			String[] priceArray = request.getParameterValues("price");
			for(int i = 0; i < idArray.length; i++) {
				System.out.println(idArray[i]);
				System.out.println(amountArray[i]);
				System.out.println(priceArray[i]);
				System.out.println("-----------------------");
			}
			String[] memberArray = request.getParameterValues("memberId");
			int memberId = Integer.parseInt(memberArray[0]);
			dao.deleteAllCart(memberId);
			System.out.println("삭제됨");
		return "cart/cart-list";
	}
	
	
}

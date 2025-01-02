package team.left.shoppingmall.cart.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartProductDto;

public class ShowCartAction implements CommandHandler {
	
	CartDao dao = new CartDao();

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memberId = (int) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);;
		System.out.println("memberId = " + memberId);
		List<CartProductDto> cartList = dao.showCart(memberId);
		request.setAttribute("cartList", cartList);

		return "cart/cart-list";
	}

}

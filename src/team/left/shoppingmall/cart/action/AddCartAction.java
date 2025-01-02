package team.left.shoppingmall.cart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartDto;
import team.left.shoppingmall.cart.dao.CartProductDto;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class AddCartAction implements CommandHandler{

	private CartDao dao = new CartDao();
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String command = request.getParameter("command");
		System.out.println("들어옴1");
		if("GET".equals(method)) {
			
			return "product/product-detail";
		}else {
			System.out.println("들어옴2");
			request.setCharacterEncoding("utf-8");
			int memberId = (int) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
			int productId = Integer.parseInt(request.getParameter("productId"));
			int amount = 1;
			CartDto cart = new CartDto(memberId, productId, amount);
			dao.addCart(cart);
		    int updatedAmount = 0;

		    response.setContentType("application/json");
		    PrintWriter out = response.getWriter();

		    try {
		        if ("increase".equals(command)) {
		            updatedAmount = dao.increaseCartAmount(productId);
		        } else if ("decrease".equals(command)) {
		            updatedAmount = dao.decreaseCartAmount(productId);
		        }
		        out.write("{\"success\": true, \"amount\": " + updatedAmount + "}");
		    } catch (Exception e) {
		        e.printStackTrace();
		        out.write("{\"success\": false}");
		    }
					
			//~~.do
			return "redirect:/cart.do?command=show-cart";
		}
	}

}


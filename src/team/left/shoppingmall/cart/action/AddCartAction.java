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

public class AddCartAction implements CommandHandler{

	private CartDao dao = new CartDao();
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String command = request.getParameter("command");
		if("GET".equals(method)) {
			return "product/product-detail";
		}else {
			request.setCharacterEncoding("utf-8");
			int member_id = Integer.parseInt(request.getParameter("member_id"));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			CartDto cart = new CartDto(member_id, product_id, amount);
			dao.addCart(cart);
		    int productId = Integer.parseInt(request.getParameter("productId"));
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


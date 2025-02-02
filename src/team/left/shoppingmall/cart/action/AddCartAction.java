package team.left.shoppingmall.cart.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.model.CartDto;
import team.left.shoppingmall.global.CommonConstants;

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
			int memberId = (int) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
			int productId = Integer.parseInt(request.getParameter("product_id"));
			int count = dao.checkCart(memberId, productId);
			System.out.println("개수는 " + count);
			if(count!=0) {
				HttpSession session = request.getSession();
			    session.setAttribute("alertMessage", "이미 장바구니에 담긴 상품입니다.");
			    return "redirect:/cart.do?command=show-cart";
			}
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


package team.left.shoppingmall.cart.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartDto;

@WebServlet("/Cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CartDao dao = new CartDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		if("add-cart".equals(command)) {
			int member_id = Integer.parseInt(request.getParameter("member_id"));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			CartDto cart = new CartDto(member_id, product_id, amount);
			try {
				dao.addCart(cart);
				response.sendRedirect("/product.do?command=detail-product");
			}catch(Exception e) {
				response.getWriter().append("Error: " + e.getMessage());
			}
		}
		
	}

}

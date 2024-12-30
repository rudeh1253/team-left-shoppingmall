package team.left.shoppingmall.cart.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.left.shoppingmall.cart.dao.CartDao;

@WebServlet("/Cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CartDao dao = new CartDao();
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String view = "/";
		System.out.println(action);
		if("show".equals(action)|| action==null) {
			request.setAttribute("action", "show");
			view="/cart.jsp";
			System.out.println(action);
		}
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views" + view);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println("설마 여기?");
		if("show".equals(action)) {
			int memberId = 1234567;
			try {
				dao.showCart(memberId);
				response.sendRedirect("/Cart.do");
			}catch(Exception e) {
				response.getWriter().append("Error: " + e.getMessage());
			}
		}
	}

}

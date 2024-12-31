package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductDto;

public class InsertProductPostAction implements CommandHandler {
	
	ProductDao dao;
	
	public InsertProductPostAction() {
		dao = new ProductDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int sellerId = 1;
		String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		ProductDto product = new ProductDto(1, sellerId, productName, description, price, stock);
		dao.insertProduct(product);
		return "redirect:/product.do?command=product";
	}

}

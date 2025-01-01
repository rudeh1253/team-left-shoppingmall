package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductDto;

public class UpdateProductGetAction implements CommandHandler {

	ProductDao dao;

	public UpdateProductGetAction() {
		dao = new ProductDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int productId = Integer.parseInt(request.getParameter("productId"));
		int productId = 1;
		ProductDto product = dao.getProductById(productId);
		request.setAttribute("product", product);
		request.setAttribute("command", request.getParameter("command"));
		return "product/productForm";
	}

}

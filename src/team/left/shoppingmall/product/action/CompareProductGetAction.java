package team.left.shoppingmall.product.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class CompareProductGetAction implements CommandHandler {
	
	private ProductDao dao;

	public CompareProductGetAction() {
		this.dao = new ProductDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ProductSpecDto> productList = new ArrayList<>();
		String[] productIds = request.getParameter("productIds").split(",");
		for (String productId : productIds) {
			productList.add(dao.getProductSpecById(Integer.parseInt(productId)));
		}
		request.setAttribute("productList", productList);
		
		return "product/product-compare";
	}

}

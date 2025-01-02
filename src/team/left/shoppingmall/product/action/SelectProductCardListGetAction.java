package team.left.shoppingmall.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.PaginationTool;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductDto;

public class SelectProductCardListGetAction implements CommandHandler {

	private ProductDao dao;

	public SelectProductCardListGetAction() {
		this.dao = new ProductDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ProductDto> productList = dao.getProductList();

		int pageCount = productList.size() / 12;
		if (productList.size() % 12 != 0) pageCount++;
		request.setAttribute("pageCount", pageCount);

		int page = 1;
		String pageStr = request.getParameter("page");
		if (pageStr != null) page = Integer.parseInt(pageStr);

		List<ProductDto> paginatedList = PaginationTool.getPaginatedList(productList, 12, page);
		request.setAttribute("productList", paginatedList);
		request.setAttribute("page", page);

		return "product/product-card-list";
	}

}

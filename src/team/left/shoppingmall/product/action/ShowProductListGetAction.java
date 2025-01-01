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
import team.left.shoppingmall.purchase.model.ReceiptDto;

public class ShowProductListGetAction implements CommandHandler {

    private ProductDao dao;

    public ShowProductListGetAction() {
        this.dao = new ProductDao();
    }

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String command = request.getParameter("command");
        List<ProductDto> productList = dao.getProductList();
        
        int pageCount = productList.size() / 7;
		if(productList.size() % 7 != 0) pageCount++;
		request.setAttribute("pageCount", pageCount);
		
		int page = 1;
		String pageStr = request.getParameter("page");
		if(pageStr != null) page = Integer.parseInt(pageStr);

		List<ProductDto> paginatedList = PaginationTool.getPaginatedList(productList, 7, page);
		request.setAttribute("productList", paginatedList);
		request.setAttribute("page", page);

        return "product/productList";
    }
}
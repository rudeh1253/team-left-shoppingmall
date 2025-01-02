package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class InsertProductPostAction implements CommandHandler {
	
	ProductDao dao;
	
	public InsertProductPostAction() {
		dao = new ProductDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int sellerId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		String productName = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
//		String thumbnail = request.getParameter("thumbnail");
		String thumbnail = "1";
		int weight = Integer.parseInt(request.getParameter("weight"));
		int screenSize = Integer.parseInt(request.getParameter("screenSize"));
		String refreshRate = request.getParameter("refreshRate");
		String displayResolution = request.getParameter("displayResolution");
		String chipset = request.getParameter("chipset");
		String cameraResolution = request.getParameter("cameraResolution");
		int batteryCapacity = Integer.parseInt(request.getParameter("batteryCapacity"));
		String description = request.getParameter("description");
		ProductSpecDto product = new ProductSpecDto(1, sellerId, productName, description, price, stock, thumbnail, weight, screenSize, refreshRate, displayResolution, chipset, cameraResolution, batteryCapacity);
		dao.insertProduct(product);
		return "redirect:/product.do?command=product";
	}

}
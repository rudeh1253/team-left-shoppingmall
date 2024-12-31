package team.left.shoppingmall.purchase.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.PurchaseDto;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;

public class InsertPurchasePostAction implements CommandHandler{
	
	private PurchaseDao purchaseDao;
	
	public InsertPurchasePostAction() {
		this.purchaseDao = new PurchaseDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 구매 성공 여부 판단
		boolean isSuccess = "success".equals(request.getParameter("isSuccess"));
		if(isSuccess) {
			int userid = (Integer)request.getSession().getAttribute("userid");
			int totalPrice = 0;
			
			String[] idArray = request.getParameterValues("productId");
			String[] amountArray = request.getParameterValues("productAmount");
			String[] priceArray = request.getParameterValues("productPrice");
			
			for(String price: priceArray) {
				totalPrice += Integer.parseInt(price);
			}
			
			int rowCount = purchaseDao.insertPurchase(totalPrice, userid);
			System.out.println("purchase 테이블에 " + rowCount + "개 행이 삽입되었습니다.");
			
			for(int i = 0; i < idArray.length; i++) {
				PurchaseProductDto dto = new PurchaseProductDto(
					0,
					Integer.parseInt(idArray[i]),
					Integer.parseInt(amountArray[i]),
					Integer.parseInt(priceArray[i])
				);
				
				rowCount = purchaseDao.insertPurchaseProduct(dto);
				System.out.println("purchase_product 테이블에 " + rowCount + "개 행이 삽입되었습니다.");
			}
			
			return "redirect:/purchase.do?command=success";
		}else {
			return "redirect:/purchase.do?command=fail";
		}
		
	}

}

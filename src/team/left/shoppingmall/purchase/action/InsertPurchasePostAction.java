package team.left.shoppingmall.purchase.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;

public class InsertPurchasePostAction implements CommandHandler{
	
	private PurchaseDao purchaseDao;
	private CartDao cartDao;
	
	public InsertPurchasePostAction() {
		this.purchaseDao = new PurchaseDao();
		this.cartDao = new CartDao();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int memberId = (Integer)request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		int totalPrice = 0;
		
		String[] idArray = request.getParameterValues("productId");
		String[] amountArray = request.getParameterValues("amount");
		String[] priceArray = request.getParameterValues("price");
		
		for(String price: priceArray) {
			totalPrice += Integer.parseInt(price);
		}
		
		int rowCount = purchaseDao.insertPurchase(totalPrice, memberId);
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
		
		cartDao.deleteAllCart(memberId);
		
		// 얘는 장바구니 데이터를 지우는 액션으로 보냄.
		return "redirect:/cart.do?command=show-cart";
	}
}

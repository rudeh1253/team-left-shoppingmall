package team.left.shoppingmall.purchase.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.cart.dao.CartDao;
import team.left.shoppingmall.cart.dao.CartProductDto;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;
import team.left.shoppingmall.purchase.model.ShipInfoDto;

public class InsertPurchasePostAction implements CommandHandler{
	
	private PurchaseDao purchaseDao;
	private CartDao cartDao;
	private ProductDao productDao;
	private MemberDao memberDao;
	
	public InsertPurchasePostAction() {
		this.purchaseDao = new PurchaseDao();
		this.cartDao = new CartDao();
		this.productDao = new ProductDao();
		this.memberDao = MemberDao.getInstance();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int memberId = (Integer)request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		int totalPrice = 0;

		List<CartProductDto> cartList = cartDao.showCart(memberId);
		
		// 총 구매 가격
		for(CartProductDto cart : cartList) {
			totalPrice += cart.getPrice();
		}
		
		ShipInfoDto shipInfo = new ShipInfoDto(
			request.getParameter("name"),
			request.getParameter("address"),
			request.getParameter("tel"),
			totalPrice
		);
		
		// 구매 데이터 삽입
		purchaseDao.insertPurchase(shipInfo, memberId);
		
		cartList.forEach((cart) -> {
			// 구매-상품 데이터 삽입
			purchaseDao.insertPurchaseProduct(
				new PurchaseProductDto(cart.getProductId(), cart.getAmount(), cart.getPrice())
			);
			
			// 판매자 포인트 증가
			memberDao.updatePointByMemberId(cart.getSellerId(), cart.getPrice());
			
			// 상품 재고수 변경
			productDao.setProductStock(cart.getProductId(), cart.getAmount());
		});
		
		
		// 장바구니 데이터 삭제
		cartDao.deleteAllCart(memberId);
		
		return "redirect:/product.do?command=card-list-product";
	}
}

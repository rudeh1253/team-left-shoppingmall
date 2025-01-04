package team.left.shoppingmall.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.product.model.ProductCountDto;
import team.left.shoppingmall.purchase.dao.PurchaseDao;

public class SellProductGetAction implements CommandHandler {
	
    

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PurchaseDao purchaseDao = new PurchaseDao();
		MemberDao memberDao = MemberDao.getInstance();
		Integer loginMemberId = (Integer) request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		String memberIdFromParameter = request.getParameter("userid");
		List<ProductCountDto> receiptList = null;
		
        boolean isMyProfile = memberIdFromParameter == null || loginMemberId.equals(Integer.parseInt(memberIdFromParameter));
        Integer memberIdToSelect = memberIdFromParameter != null ? Integer.parseInt(memberIdFromParameter) : loginMemberId;
		
		receiptList = purchaseDao.getSellCount(loginMemberId);

		
		request.setAttribute("receiptList", receiptList);
		request.setAttribute("userid", loginMemberId);
		request.setAttribute("role", memberDao.findRoleByMemberId(loginMemberId));
		request.setAttribute("isMyProfile", isMyProfile);
		
		
		return "product/product-sell-chart";
	}
	
}

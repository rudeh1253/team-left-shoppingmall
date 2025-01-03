package team.left.shoppingmall.purchase.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.PurchaseDetailDto;

public class PurchaseDetailGetAction implements CommandHandler{
	
	private PurchaseDao purchaseDao;
	private MemberDao memberDao;
	
	
	public PurchaseDetailGetAction() {
		this.purchaseDao = new PurchaseDao();
		this.memberDao = MemberDao.getInstance();
	}
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		int memberId = (Integer)request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		
		String role = memberDao.findRoleByMemberId(memberId);
		if("buy".equals(role)) {
			request.setAttribute("command", "purchase");
		}else {
			request.setAttribute("command", "sell");
		}
		
		PurchaseDetailDto detailDto = purchaseDao.findPurchaseDetail(purchaseId, productId);
		request.setAttribute("detailDto", detailDto);

		return "purchase/purchase-detail";
	}
}

package team.left.shoppingmall.purchase.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.global.PaginationTool;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.ReceiptDto;

public class FindPurchaseListGetAction implements CommandHandler{
	PurchaseDao purchaseDao;

	public FindPurchaseListGetAction() {
		this.purchaseDao = new PurchaseDao();
	}
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		int memberId = (Integer)request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		List<ReceiptDto> receiptList = null;
		
		if("purchase".equals(command)) {
			receiptList = purchaseDao.getPurchaseReceipt(memberId);
			request.setAttribute("title", "구매내역");
		}else {
			receiptList = purchaseDao.getSellReceipt(memberId);
			request.setAttribute("title", "판매내역");
		}
		int pageCount = receiptList.size() / 7;
		if(receiptList.size() % 7 != 0) pageCount++;
		request.setAttribute("pageCount", pageCount);
		
		int page = 1;
		String pageStr = request.getParameter("page");
		if(pageStr != null) page = Integer.parseInt(pageStr);

		List<ReceiptDto> paginatedList = PaginationTool.getPaginatedList(receiptList, 7, page);
		request.setAttribute("receiptList", paginatedList);
		request.setAttribute("page", page);
		return "purchase/purchase-list";
	}

}

package team.left.shoppingmall.purchase.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.purchase.dao.PurchaseDao;
import team.left.shoppingmall.purchase.model.ReceiptDto;

public class GetPurchaseListAction implements CommandHandler{
	PurchaseDao purchaseDao;

	public GetPurchaseListAction() {
		this.purchaseDao = new PurchaseDao();
	}
	
	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		// int userid = (Integer)request.getSession().getAttribute("userid");
		List<ReceiptDto> receiptList = null;
		
		if("purchase".equals(command)) {
			receiptList = purchaseDao.getPurchaseReceipt(1);
			request.setAttribute("title", "구매내역");
		}else {
			receiptList = purchaseDao.getSellReceipt(2);
			request.setAttribute("title", "판매내역");
		}
		
		request.setAttribute("receiptList", receiptList);
		return "purchase/purchaselist";
	}

}

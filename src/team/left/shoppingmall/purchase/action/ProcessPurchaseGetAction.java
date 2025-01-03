package team.left.shoppingmall.purchase.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.purchase.model.ShipInfoDto;

public class ProcessPurchaseGetAction implements CommandHandler {
	
	private MemberDao memberDao;
	
	public ProcessPurchaseGetAction() {
		this.memberDao = MemberDao.getInstance();
	}

	@Override
	public String handleCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int memberId = (int)request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
		ShipInfoDto shipInfo = memberDao.findShipInfoById(memberId);
		request.setAttribute("shipInfo", shipInfo);
		
		return "purchase/purchase-process";
	}

}

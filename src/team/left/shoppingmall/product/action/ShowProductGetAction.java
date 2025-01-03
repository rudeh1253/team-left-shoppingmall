package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;
import team.left.shoppingmall.member.dao.SelectMemberDto;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class ShowProductGetAction implements CommandHandler {

    private ProductDao dao;
    

    public ShowProductGetAction() {
        this.dao = new ProductDao();
    }

    
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 상품 ID를 요청에서 가져오기
            String productId = request.getParameter("productId");
            Object memberIdObj = request.getSession().getAttribute(CommonConstants.MEMBER_SESSION_KEY);
            int memberId = 0;
            if (memberIdObj != null) {
                memberId = (int) memberIdObj;
            } else {
                memberId = -1;
            }
            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("상품 ID가 누락되었습니다.");
            }
            System.out.println(productId);
            // 상품 상세 정보 조회
            ProductSpecDto product = dao.getProductSpecById(Integer.parseInt(productId));
            System.out.println(product.toString());
            
            if(memberId>=0) {
                SelectMemberDto member = dao.showMember(memberId);
                request.setAttribute("member", member);               
             }else {
                SelectMemberDto member = new SelectMemberDto();
                member.setRole("sell");
                request.setAttribute("member", member);
             }
   
             SelectMemberDto seller = dao.showMember(product.getSellerId());
             
             // 조회 결과를 요청에 추가
             request.setAttribute("product", product);
             request.setAttribute("memberId", memberId);
             request.setAttribute("seller", seller);
             request.setAttribute("isEdit", true);

            // JSP로 포워딩
            return "product/product-detail";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "상품 정보를 불러오는 도중 문제가 발생했습니다.");
            return "error/error-page";
        }
    }
}

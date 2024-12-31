package team.left.shoppingmall.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductDto;

public class ShowRegisterListGetAction implements CommandHandler {

    private ProductDao dao;

    public ShowRegisterListGetAction() {
        this.dao = new ProductDao(); // ProductDao 인스턴스 초기화
    }

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // seller_id를 요청 파라미터로부터 가져오기
            int sellerId = Integer.parseInt(request.getParameter("seller_id"));

            // 해당 seller_id가 등록한 상품 목록 조회
            List<ProductDto> productList = dao.getProductsBySellerId(sellerId);

            // 상품 목록을 request 객체에 추가
            request.setAttribute("productList", productList);

            // 상품 목록 JSP 경로 반환
            return "product/register-list"; // JSP 파일 경로
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error-page"; 
        }
        
    }
}
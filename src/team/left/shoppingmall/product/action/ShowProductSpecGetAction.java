package team.left.shoppingmall.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class ShowProductSpecGetAction implements CommandHandler {

    private ProductDao dao;

    public ShowProductSpecGetAction() {
        this.dao = new ProductDao();
    }

    
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 상품 ID를 요청에서 가져오기
            String productId = request.getParameter("productId");
            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("상품 ID가 누락되었습니다.");
            }

            // 상품 상세 정보 조회
            //ProductSpecDto productSpec = dao.getProductSpecByProductId(Integer.parseInt(productId));

            // 조회 결과를 요청에 추가
            //request.setAttribute("productDetail", productDetail);

            // JSP로 포워딩
            return "product/product-spec";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "상품 정보를 불러오는 도중 문제가 발생했습니다.");
            return "error/error-page";
        }
    }
}

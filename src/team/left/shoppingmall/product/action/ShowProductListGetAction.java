package team.left.shoppingmall.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.product.dao.ProductDao;
import team.left.shoppingmall.product.model.ProductDto;

public class ShowProductListGetAction implements CommandHandler {

    private ProductDao dao;

    public ShowProductListGetAction() {
        this.dao = new ProductDao();
    }

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 상품 목록 조회
            List<ProductDto> productList = dao.showAllProducts();

            // 요청 객체에 상품 목록 추가
            request.setAttribute("productList", productList);
            
            return "product/product-list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error-page"; 
        }
    }
}
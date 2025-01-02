<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .product-detail-container {
            display: flex;
            max-width:   1000px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        .thumbnail {
            flex: 0 0 auto;
            margin-right: 30px; /* 이미지와 설명 사이 간격 */
        }
        .thumbnail img {
            width: 300px;
            height: 250px;
            margin-right: 30px;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 5px;
        }
        .product-info {
            flex: 1;
            margin-left: 30px;
        }
        .product-info h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        .product-info p {
            margin: 5px 0;
        }
        .thumbnail {
            flex: 0 0 200px;
            text-align: center;
        }
        .cart-form {
            text-align: center;
            margin-top: 20px;
        }
        .cart-form button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .cart-form button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
<%@ page import="java.time.LocalDate" %>
<%
    // 현재 날짜 가져오기
    LocalDate today = LocalDate.now();
    String formattedDate = today.toString(); // yyyy-MM-dd 형식
%>
<%@include file="/WEB-INF/views/common/header.jsp"%>

	<c:choose>
	    <c:when test="${product.sellerId==memberId}">
	        <form action="/product.do?command=edit-detail" method="POST">
	    </c:when>
	    <c:when test="${member.role=='sell'}">
	    </c:when>
	    <c:otherwise>
		    <form action="/cart.do?command=add-cart" method="POST">
	    </c:otherwise>
	</c:choose>
	<h1 style="margin-left: 350px;">제품 상세 정보</h1>
	<div class="product-detail-container">
	    <div class="thumbnail">
	        <img src="/resources/images/default-product-image.png" data-filename="default-product-image.png" alt="상품 이미지"/>
	        </br></br><p><strong>가격:</strong>&nbsp${product.price}원</p>
	    </div>
	    <div class="product-info">
	        <p><strong>상품명:</strong>&nbsp${product.productName}</p>
	        <p><strong>seller id:</strong>&nbsp${product.sellerId}</p>
	        <dl><strong>설명:</strong>&nbsp${product.description}</dl>
	    </div>
	    <div class="product-info">
	       <p><strong>화면 크기(inch):</strong>&nbsp${product.screenSize}</p>
	        <p><strong>주사율:</strong>&nbsp${product.refreshRate}</p>
	        <p><strong>화면 해상도:</strong>&nbsp${product.displayResolution}</p>
	        <p><strong>카메라 해상도:</strong>&nbsp${product.cameraResolution}</p>
	        <p><strong>배터리 용량:</strong>&nbsp${product.batteryCapacity}</p>
	        </br></br></br>
	        <div class="cart-form">
	       <form action="cart.do?command=add-cart" method="post">
	           <input type="hidden" name="product_id" value="${product.productId}">
	           <input type="hidden" name="product_name" value="${product.productName}">
	           <input type="hidden" name="price" value="${product.price}">
	           <!--<input type="number" name="quantity" min="1" value="1" placeholder="수량">  -->
           <c:choose>
			    <c:when test="${product.sellerId==memberId}">
			        <input type="submit"  value="상세정보 수정" style="background-color: #007bff; color: white; font-size: 16px; padding: 10px 20px; border: none; border-radius: 25px; cursor: pointer;">
			    </c:when>
			    <c:when test="${member.role=='sell'}">
			    </c:when>
			    <c:otherwise>
				    <input type="submit"  value="장바구니 담기" style="background-color: #007bff; color: white; font-size: 16px; padding: 10px 20px; border: none; border-radius: 25px; cursor: pointer;">
			    </c:otherwise>
			</c:choose>
       </form>
      </div>
	</div>
	</div>
</form>

 <%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
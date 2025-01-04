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
        .product-detail-container p:first-child{
            margin-bottom: 5px;
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
	    <c:when test="${(member.role).equals('sell')}">
	    </c:when>
	    <c:otherwise>
		    <form action="/cart.do?command=add-cart" method="POST">
	    </c:otherwise>
	</c:choose>
	<h1 class="fw-bold" style="margin-left: 350px; margin-top:50px">상품 상세 정보</h1>
	<div style="display: flex; justify-content: center; align-items: flex-start; padding-left: 300px; width: 80vw">
	    <div class="thumbnail" style="flex: 0 0; padding-top: 20px;">
	        <img src="${product.thumbnail }" data-filename="${product.thumbnail }" alt="상품 이미지" style="width: 500px; height: auto;"/>
		   
	    </div>
		<div class="product-detail-container product-detail" style="flex: 1; flex-direction: column;">
		        <p style="font-size: 30px; font-weight: bold;">${product.productName}</p>
		         <p><a href="/member.do?command=profile&userid=${product.sellerId}" style="text-decoration: none;">${seller.member_name}</a>&nbsp(${seller.company})</p>
		         <p style="font-size: 20px; font-weight: bold; color: blue;"><fmt:formatNumber value="${product.price}" pattern="#,###" />원</p>
		        
		        <dl><strong>설명:</strong>&nbsp${product.description}</dl>
		        <p><strong>화면 크기(inch):</strong>&nbsp${product.screenSize} inch</p>
		        <p><strong>주사율:</strong>&nbsp${product.refreshRate} hz</p>
		        <p><strong>화면 해상도:</strong>&nbsp${product.displayResolution}</p>
		        <p><strong>카메라 해상도:</strong>&nbsp${product.cameraResolution} 화소</p>
		        <p><strong>배터리 용량:</strong>&nbsp${product.batteryCapacity} mAh</p>
		        </br>
		        <div class="cart-form">
		           <input type="hidden" name="product_id" value="${product.productId}">
		           <input type="hidden" name="product_name" value="${product.productName}">
		           <input type="hidden" name="price" value="${product.price}">
		           <!--<input type="number" name="quantity" min="1" value="1" placeholder="수량">  -->
	           <c:choose>
				    <c:when test="${product.sellerId==memberId}">
				        <button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="background-color: #007bff; color: white; font-size: 16px; padding: 10px 20px; border: none; border-radius: 25px; cursor: pointer;">상세정보 수정</button>
				    </c:when>
				    <c:when test="${(member.role).equals('sell')}">
				    </c:when>
				    <c:otherwise>
					    <input type="submit"  value="장바구니 담기" style="background-color: #007bff; color: white; font-size: 16px; padding: 10px 20px; border: none; border-radius: 25px; cursor: pointer;">
				    </c:otherwise>
				</c:choose>
	       </form>
	 
		</div>
		<br><br>
		</div>
	</div>
</form>
<%@include file="/WEB-INF/views/product/product-form-modal.jsp"%>
<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
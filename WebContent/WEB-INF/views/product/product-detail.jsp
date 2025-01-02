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
            max-width:	1000px;
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
</div>
<h1 class="text-center">상품 상세 정보</h1>
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
        	<button type="submit">장바구니 추가</button>
    	</form>
		</div>
</div>
</div>
<!--
<form action="cart.do?command=add-cart" method="post">
	member id: <input type="number" name="member_id"><br>
	product id: <input type="number" name="product_id"><br>
	seller id: <input type="number" name="seller_id"><br>
	date: <input type="date" name="reg_date" value="<%= formattedDate %>"><br>
	product name: <input type="text" name="product_name"><br>
	description: <input type="text" name="description"><br>
	price: <input type="number" name="price"><br>
	
	weight: <input type="number" name="weight">
	화면 크기(inch): <input type="number" name="screen_size">
	주사율(Hz): <input type="number" name="refresh_rate">
	화면 해상도: <input type="number" name="display_resolution">
	칩셋: <input type="text" name="chipset">
	카메라 해상도: <input type="number" name="camera_resolution">
	배터리 용량: <input type="number" name="battery_capacity">
	stock: <input type="number" name="stock"><br>
	thumbnail: <input type="text" name="thumbnail"><br>
	개수 : <input type="number" name="amount"><br>
	
	<input type="hidden" name="command" value="add-cart">
	<button type="submit">상품등록</button>
</form>
 -->
 <%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
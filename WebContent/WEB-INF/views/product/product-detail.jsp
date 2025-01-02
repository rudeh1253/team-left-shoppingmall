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
            justify-content: space-between;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        .thumbnail img {
            max-width: 150%;
            height: auto;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 5px;
        }
        .product-info {
            flex: 1;
            margin-right: 20px;
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
<form action="/cart.do?command=add-cart" method="POST">
<h1>제품 상세 정보</h1>
<div class="product-detail-container">
    <div class="thumbnail">
        <p>${product.thumbnail}</p>
    </div>
    <div class="product-info">
        <p><strong>상품명:</strong>${product.productName}</p>
        <p><strong>seller id:</strong>${product.sellerId}</p>
        <p><strong>설명:</strong>${product.description}</p>
        <p><strong>가격:</strong>${product.price}원</p>
        <p><strong>화면 크기(inch):</strong>${product.screenSize}</p>
        <p><strong>주사율:</strong>${product.refreshRate}</p>
        <p><strong>화면 해상도:</strong>${product.displayResolution}</p>
        <p><strong>카메라 해상도:</strong>${product.cameraResolution}</p>
        <p><strong>배터리 용량:</strong>${product.batteryCapacity}</p>
    </div>
    <input type="hidden" name="productId" value="${product.productId}" />
    <input type="submit"  value="장바구니 담기">
</div>
</form>
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
</body>
<%@include file="/WEB-INF/views/common/footer.jsp"%>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.time.LocalDate" %>
<%
    // 현재 날짜 가져오기
    LocalDate today = LocalDate.now();
    String formattedDate = today.toString(); // yyyy-MM-dd 형식
%>

<form action="cart.do?command=add-cart" method="post">
	member id: <input type="number" name="member_id"><br>
	product id: <input type="number" name="product_id"><br>
	seller id: <input type="number" name="seller_id"><br>
	date: <input type="date" name="reg_date" value="<%= formattedDate %>"><br>
	product name: <input type="text" name="product_name"><br>
	description: <input type="text" name="description"><br>
	price: <input type="number" name="price"><br>
	stock: <input type="number" name="stock"><br>
	thumbnail: <input type="text" name="thumbnail"><br>
	개수 : <input type="number" name="amount"><br>
	<input type="hidden" name="command" value="add-cart">
	<button type="submit">상품등록</button>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<div class="container mt-4">
		<h2>게시판</h2>
		<div class="mb-3">
			<a href="write.jsp" class="btn btn-primary">글 작성</a>
		</div>

		<!-- 게시글 목록 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>썸네일</th>
					<th>상품ID</th>
					<th>개수</th>
					<th>판매자</th>
					<th>담은 날짜</th>
					<th>상품명</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cart" items="${cartList}">
					<tr>
						<td>${cart.thumbnail}</td>
						<td>${cart.productId}</td>
						<td>${cart.amount}</td>
						<td>${cart.sellerId}</td>
						<td>${cart.regdate}</td>
						<td>${cart.productName}</td>
						<td>${cart.price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Bootstrap JS CDN -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
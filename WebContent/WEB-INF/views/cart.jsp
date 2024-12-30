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
</head>
<body>
	<form action="/Cart.do"  method="POST">
		<div class="container mt-4">
			<h2>게시판</h2>
			<div class="mb-3">
				<a href="write.jsp" class="btn btn-primary">글 작성</a>
			</div>

			<!-- 게시글 목록 -->
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td><a href="view.jsp?id=1">게시글 제목 1</a></td>
						<td>${cart.getProductId()}</td>
						<td>2024-12-30</td>
					</tr>
					<tr>
						<td>2</td>
						<td><a href="view.jsp?id=2">게시글 제목 2</a></td>
						<td>김철수</td>
						<td>2024-12-29</td>
					</tr>
					<!-- 추가적인 게시글을 여기에 작성 -->
				</tbody>
			</table>
		</div>
		<input type="hidden" name="action" value="${command}">
		<!-- Bootstrap JS CDN -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
		
	</form>
</body>
</html>
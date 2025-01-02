<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<div class="row justify-content-between mt-5 align-items-center">
			<div class="col fs-1">상품 목록</div>
			<div class="col text-end">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">등록하기</button>
			</div>
		</div>
		<table class="table table-hover text-center mt-3 align-middle">
			<thead class="table-light">
				<tr>
					<th scope="col">이미지</th>
					<th scope="col">상품명</th>
					<th scope="col">가격</th>
					<th scope="col">재고</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="product" items="${productList }">
					<tr class="align-items-center">
						<td class="w-25 h-25">
							<div class="w-50 mx-auto">
								<img src="/resources/images/default-product-image.png" data-filename="default-product-image.png" alt="상품 이미지" class="img-fluid" />
							</div>
						</td>
						<td><a href="${product.productId}" class="link-underline link-underline-opacity-0">${product.productName }</a></td>
						<td>${product.price }</td>
						<td>${product.stock }</td>
						<td>${product.regDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation example"
			class="d-flex justify-content-center">
			<ul class="pagination">
				<li class="page-item"><a class="page-link"
					href="/product.do?command=<%= request.getParameter("command") %>&page=1"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="i" begin="1" end="${pageCount}">
					<li class="page-item"><a
						class="page-link <c:if test='${page == i}'>active</c:if>"
						href="/product.do?command=<%= request.getParameter("command") %>&page=${i}">${i}</a></li>
				</c:forEach>
				<li class="page-item"><a class="page-link"
					href="/product.do?command=<%= request.getParameter("command") %>&page=${pageCount}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
	<%@include file="/WEB-INF/views/product/productFormModal.jsp"%>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
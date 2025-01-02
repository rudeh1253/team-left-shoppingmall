<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">이미지</th>
					<th scope="col">상품명</th>
					<th scope="col">가격</th>
					<th scope="col">재고</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="product" items="${productList}">
					<tr>
						<td><img src="${product.thumbnail}" alt="상품 이미지" class="img-thumbnail" /></td>
						<td><a href="/product.do?command=detail-product&productId=${product.productId}">${product.productName }</a></td>
						<td>${product.price}</td>
						<td>${product.stock}</td>
						<td>${product.regDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation example" class="d-flex justify-content-center mt-4">
			<ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="/product.do?command=<%= request.getParameter("command") %>&page=1" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="i" begin="1" end="${pageCount}">
            		<li class="page-item"><a class="page-link <c:if test='${page == i}'>active</c:if>" href="/product.do?command=<%= request.getParameter("command") %>&page=${i}">${i}</a></li>
           		</c:forEach>
                <li class="page-item">
                    <a class="page-link" href="/product.do?command=<%= request.getParameter("command") %>&page=${pageCount}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
		</nav>
	</div>
	<%@include file="/WEB-INF/views/product/productFormModal.jsp"%>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
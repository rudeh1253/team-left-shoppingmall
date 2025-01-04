<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Left 쇼핑몰</title>

<style>
.banner {
	position: relative;
	display: flex;
	align-items: center;
	justify-content: center;
	overflow: hidden;
	height:300px;
	margin-top:60px;
}

.banner img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	object-fit: contain;
}

</style>

</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="banner">
		<img src="/resources/images/iphone_banner.png">
	</div>

	<div class="container" style="min-height: 76vh">
		<c:if test="${empty productList}">
			<h1 class="text-center">검색 결과가 없습니다!</h1>
		</c:if>
		<c:if test="${!empty productList}">
			<div class="row row-cols-1 row-cols-md-4 g-4 my-4 w-75 mx-auto">
				<c:forEach var="product" items="${productList}">
					<a style="border: 2px; border-radius: 12px; padding: 2;"
						class="text-decoration-none"
						href="/product.do?command=detail-product&productId=${product.productId}">
						<div class="col" style="margin-right: 5px">
							<div class="card h-100">
								<img src="${product.thumbnail }"
									data-filename="${product.thumbnail }"
									class="card-img-top img-thumbnail" alt="상품 이미지">
								<div class="card-body">
									<h5 class="card-title fw-bold" style="font-size: 20px">${product.productName }</h5>
									<p class="card-text">
										<fmt:formatNumber value="${product.price }" pattern="#,###" />
										원
									</p>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
			<nav aria-label="Page navigation example"
				class="d-flex justify-content-center">
				<ul class="pagination">
					<li class="page-item"><a class="page-link"
						href="/product.do?command=<%=request.getParameter("command")%>&page=1&keyword=<%=request.getParameter("keyword")%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<c:forEach var="i" begin="1" end="${pageCount}">
						<li class="page-item"><a
							class="page-link <c:if test='${page == i}'>active</c:if>"
							href="/product.do?command=<%= request.getParameter("command") %>&page=${i}&keyword=<%= request.getParameter("keyword") %>">${i}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link"
						href="/product.do?command=<%= request.getParameter("command") %>&page=${pageCount}&keyword=<%= request.getParameter("keyword") %>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</c:if>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드리스트</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
        <div class="row row-cols-1 row-cols-md-4 g-4 my-4 w-75 mx-auto">
        	<c:forEach var="product" items="${productList }">
	            <div class="col">
	                <div class="card h-100">
	                    <img src="/resources/images/default-product-image.png" data-filename="default-product-image.png" class="card-img-top img-thumbnail" alt="상품 이미지">
	                    <div class="card-body">
	                        <h5 class="card-title">${product.productName }</h5>
	                        <p class="card-text">${product.price }</p>
	                    </div>
	                </div>
	            </div>
        	</c:forEach>
        </div>
        <nav aria-label="Page navigation example"
			class="d-flex justify-content-center">
			<ul class="pagination">
				<li class="page-item"><a class="page-link"
					href="/product.do?command=<%= request.getParameter("command") %>&page=1&keyword=<%= request.getParameter("keyword") %>"
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
    </div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
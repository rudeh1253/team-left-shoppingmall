<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<script>
//메뉴 토글
function toggleMenu(menuId) {
    const menus = document.querySelectorAll('.menu');
    menus.forEach(menu => {
        if (menu.id !== menuId) { 
            menu.classList.add('hidden');
        }
    });

    // 클릭된 메뉴 가져오기
    const currentMenu = document.getElementById(menuId);
    if (currentMenu) {
        currentMenu.classList.toggle('hidden');
    }
}


</script>

<style>
.hambergerButton {
	width: 30px;
	height: 30px;
	position: absolute;
	right: 7px;
	top: 34%;
	cursor: pointer;
	border: 1px solid grey;
	user-select: none;
	color: white;
}

.hambergerMenu {
	display: flex;
	justify-content: center;
	align-content: center;
	width: 120px;
	position: absolute;
	right: -120px;
	top: 8px;
	border: 2px solid grey;
	cursor: pointer;
	flex-direction: column;
	user-select: none;
	color: white;
}

.hidden {
	display: none;
}

.menu {
	background-color: #f8f9fa;
	text-align: center;
}
</style>

</head>
<body>
	<%
		//@include file="/WEB-INF/views/common/header.jsp"
	%>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center"
			style="flex: 1; padding: 1rem; overflow: auto; margin: 0 70px 100px 0">
			<div class="outer-box" style="padding: 0px; width: 52vw">
				<div class="container">
					<c:if test="${empty productList}">
						<div class="d-flex justify-content-center mt-5 align-items-center" style="height:80vh;">
							<div class="d-flex flex-column">
								<h1 class="text-center fw-semibold">등록한 물품이 없습니다!</h1>
								<hr>
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop">등록하기</button>
							</div>
						</div>
					</c:if>
					<c:if test="${!empty productList}">
						<div class="container" style="min-height: 50vh">
							<div class="row justify-content-between mt-5 align-items-center">
								<div class="col fs-1 fw-bolder">상품 목록</div>
								<c:if test="${sessionScope.member ne null }">
									<div class="col text-end">
										<c:if test="${isMyProfile }">
											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal" data-bs-target="#staticBackdrop">등록하기</button>
										</c:if>
									</div>
								</c:if>
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
									<c:forEach var="product" items="${productList }"
										varStatus="status">
										<tr class="align-items-center" style="position: relative">
											<td class="w-25 h-25">
												<div class="w-50 mx-auto">
													<img src="${product.thumbnail }" width="70px"
														data-filename="default-product-image.png" alt="상품 이미지"
														class="img-fluid" />
												</div>
											</td>
											<td><a
												href="/product.do?command=detail-product&productId=${product.productId}"
												class="link-dark link-underline link-underline-opacity-0">${product.productName }</a></td>
											<td>${product.price }</td>
											<td>${product.stock }</td>
											<td>${product.regDate }</td>
											<c:if test="${ isMyProfile }">
												<td id="hamberger${status.index + 1}"
													class="d-flex justify-content-center align-items-center border rounded hambergerButton"
													onclick="toggleMenu('menu${status.index + 1}')"
													draggable="false"
													style="background-color: #0d6EFD; color: white">☰</td>
												<td id="menu${status.index + 1}"
													class="menu hidden hambergerMenu rounded border "><a
													href="/purchase.do?id=${product.productId}"
													class="text-decoration-none active bg-primary rounded text-light">수정하기</a>
													<hr style="margin: 5px"> <a
													href="/product.do?command=delete-product&productId=${product.productId}"
													class="text-decoration-none active bg-danger rounded text-light">삭제하기</a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<nav aria-label="Page navigation example"
								class="d-flex justify-content-center">
								<ul class="pagination">
									<li class="page-item"><a class="page-link"
										href="/product.do?command=<%=request.getParameter("command")%>&page=1&userid=<%=request.getParameter("userid")%>"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>
									<c:forEach var="i" begin="1" end="${pageCount}">
										<li class="page-item"><a
											class="page-link <c:if test='${page == i}'>active</c:if>"
											href="/product.do?command=<%= request.getParameter("command") %>&page=${i}&userid=<%= request.getParameter("userid") %>">${i}</a></li>
									</c:forEach>
									<li class="page-item"><a class="page-link"
										href="/product.do?command=<%= request.getParameter("command") %>&page=${pageCount}&userid=<%= request.getParameter("userid") %>"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
									</a></li>
								</ul>
							</nav>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/product/product-form-modal.jsp"%>
</body>
</html>
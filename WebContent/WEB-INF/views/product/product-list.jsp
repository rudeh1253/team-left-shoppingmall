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
    // 모든 메뉴 닫기
    const menus = document.querySelectorAll('.menu');
    menus.forEach(menu => {
        if (menu.id !== menuId) {  // 현재 클릭한 메뉴를 제외한 나머지 메뉴는 숨김
            menu.classList.add('hidden');
        }
    });

    // 클릭된 메뉴 가져오기
    const currentMenu = document.getElementById(menuId);
    if (currentMenu) {
        // currentMenu가 숨겨져 있지 않으면 숨기고, 숨겨져 있으면 열기
        currentMenu.classList.toggle('hidden');
    }
}


// 수정하기
function editItem(index) {
    alert(`수정하기: ${index}`);
    // 수정 로직 추가
}

// 삭제하기
function deleteItem(index) {
    if (confirm('정말 삭제하시겠습니까?')) {
        alert(`삭제하기: ${index}`);
        // 삭제 로직 추가
    }
}


</script>

<style>
.hambergerButton {
	width: 30px;
	height: 30px;
	position: absolute;
	right: -55px;
	top: 25px;
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
	right: -200px;
	top: 0px;
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
					<div class="container" style="min-height: 50vh">
						<div class="row justify-content-between mt-5 align-items-center">
							<div class="col fs-1">상품 목록</div>
							<c:if test="${sessionScope.member ne null }">
								<div class="col text-end">
									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop">등록하기</button>
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
												<img src="/resources/images/default-product-image.png"
													data-filename="default-product-image.png" alt="상품 이미지"
													class="img-fluid" />
											</div>
										</td>
										<td><a
											href="/product.do?command=detail-product&productId=${product.productId}"
											class="link-underline link-underline-opacity-0">${product.productName }</a></td>
										<td>${product.price }</td>
										<td>${product.stock }</td>
										<td>${product.regDate }</td>
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
											href="/delete.do?id=${product.productId}"
											class="text-decoration-none active bg-danger rounded text-light">삭제하기</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<nav aria-label="Page navigation example"
							class="d-flex justify-content-center">
							<ul class="pagination">
								<li class="page-item"><a class="page-link"
									href="/product.do?command=<%=request.getParameter("command")%>&page=1"
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
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/product/product-form-modal.jsp"%>
</body>
</html>
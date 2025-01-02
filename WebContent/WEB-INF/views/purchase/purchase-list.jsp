<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${ title }</title>
</head>


<body>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center"
			style="flex: 1; padding: 1rem; overflow: auto; margin: 50px 0 100px 0">
			<div class="outer-box" style="padding: 0px; width: 60vw">
				<div class="container">
					<h2 style="margin:70px 0 100px 0">${ title }</h2>
					<table class="table text-center align-middle rounded table-hover">
						<thead class="">
							<tr>
								<th>이미지</th>
								<th>상품 명</th>
								<th>가격</th>
								<th>수량</th>
								<th>합계</th>
								<th>배송 상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="receipt" items="${receiptList}"
								varStatus="status">
								<tr style="position: relative">
									<td><img src="${receipt.thumbnail}" alt="상품 이미지"
										width="50px" class="img-thumbnail" /></td>
									<td>${receipt.productName}</td>
									<td>${receipt.price}원</td>
									<td>${receipt.amount}</td>
									<td>${receipt.totalPrice}원</td>
									<td>${receipt.state}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<nav aria-label="Page navigation"
						class="d-flex justify-content-center mt-4">
						<ul class="pagination">
							<li class="page-item"><a class="page-link"
								href="/purchase.do?command=<%=request.getParameter("command")%>&page=1"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<c:forEach var="i" begin="1" end="${pageCount}">
								<li class="page-item"><a
									class="page-link <c:if test='${page == i}'>active</c:if>"
									href="/purchase.do?command=<%= request.getParameter("command") %>&page=${i}">${i}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link"
								href="/purchase.do?command=<%= request.getParameter("command") %>&page=${pageCount}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
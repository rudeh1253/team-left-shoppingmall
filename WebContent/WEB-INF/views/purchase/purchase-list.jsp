	<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>

</head>
<body>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center"
			style="flex: 1; padding: 1rem; overflow: auto; margin: 0 70px 60px 0">
			<c:if test="${empty receiptList}">
					<div class="d-flex justify-content-center mt-5 align-items-center" style="height: 80vh;">
					<div class="d-flex flex-column">
						<h1 class="text-center fw-semibold"> ${title} 목록이 없습니다.</h1>
					</div>
				</div>
			</c:if>
			<c:if test="${!empty receiptList}">
			<div class="outer-box" style="padding: 0px; width: 52vw">
                <div class="container">
                    <div class="row justify-content-between mt-5 align-items-center">
                        <div class="col fs-1 fw-bolder">${title}</div>
                    </div>
                    <table class="table table-hover text-center mt-3 align-middle min-vh-50">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">이미지</th>
                                <th scope="col">상품 명</th>
                                <th scope="col">수량</th>
                                <th scope="col">합계</th>
                                <th scope="col">결제일</th>
                                <th scope="col">배송 상태</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:forEach var="receipt" items="${receiptList}" varStatus="status">
                                <tr class="align-items-center" style="position: relative">
                                    <td class="w-25 h-25">
                                        <div class="w-50 mx-auto">
                                            <img src="${receipt.thumbnail}" alt="상품 이미지" class="img-fluid" width="70px"/>
                                        </div>
                                    </td>
                                    <td><a class="link-dark link-underline link-underline-opacity-0" href="/purchase.do?command=purchase-detail&purchaseId=${receipt.purchaseId}&productId=${receipt.productId}">${receipt.productName}</a></td>
                                    <td>${receipt.amount}</td>
                                    <td><fmt:formatNumber value="${receipt.totalPrice}" pattern="#,###" />원</td>
                                    <td>${receipt.purchaseDate}</td>
                                    <td>${receipt.state}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link"
                                    href="/purchase.do?command=<%=request.getParameter("command")%>&page=1"
                                    aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach var="i" begin="1" end="${pageCount}">
                                <li class="page-item">
                                    <a class="page-link <c:if test='${page == i}'>active</c:if>"
                                        href="/purchase.do?command=<%= request.getParameter("command") %>&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link"
                                    href="/purchase.do?command=<%= request.getParameter("command") %>&page=${pageCount}"
                                    aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            </c:if>
        </div>
    </div>
</body>
</html>

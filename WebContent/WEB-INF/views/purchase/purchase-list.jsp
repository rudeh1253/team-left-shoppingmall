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
			<div class="outer-box" style="padding: 0px; width: 52vw">
                <div class="container">
                    <div class="row justify-content-between mt-5 align-items-center">
                        <div class="col fs-1">${title}</div>
                    </div>
                    <table class="table table-hover text-center mt-3 align-middle">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">이미지</th>
                                <th scope="col">상품 명</th>
                                <th scope="col">가격</th>
                                <th scope="col">수량</th>
                                <th scope="col">합계</th>
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
                                    <td>${receipt.productName}</td>
                                    <td>${receipt.price}원</td>
                                    <td>${receipt.amount}</td>
                                    <td>${receipt.totalPrice}원</td>
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
        </div>
    </div>
</body>
</html>

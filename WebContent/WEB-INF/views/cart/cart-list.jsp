<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>

<script src="/resources/js/cart/cart-list.js"></script>

<style>

	td.xButton {
	    position: absolute;
	    border: none;
	    background: transparent;  /* 배경을 투명으로 설정 */
	    box-shadow: none;  /* 그림자 없애기 */
	}
	

	table tfoot td{
		background-color: #e9f1f7 !important;
	}


  </style>
</head>
<body>
<c:if test="${not empty sessionScope.alertMessage}">
    <script>
        alert("${sessionScope.alertMessage}");
        window.location.href = '/cart.do?command=show-cart'; // 리디렉션
    </script>
    <c:set var="alertMessage" value="${sessionScope.alertMessage}" />
    <c:remove var="alertMessage" scope="session" />
</c:if>
	<div class="container mt-4" style="min-height: 76vh">
		<h2>장바구니</h2>
		<c:choose>
            <c:when test="${not empty cartList}">
				<!-- 게시글 목록 -->
				<table class="table table-hover text-center mt-3 align-middle">
					<thead class="table-light">
						<tr>
							<th></th>
							<th class="align-center" style="text-align: center;">이미지</th>
							<th>상품명</th>
							<th>판매자</th>
							<th>수량</th>
							<th></th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<c:forEach var="cart" items="${cartList}">
							<tr class="align-middle" data-product-id="${cart.productId}" data-price="${cart.price}" data-amount="${cart.amount}">
								<td><input type="checkbox" class="form-check-input" value="${cart.productId }"></td>
								<td class="d-flex align-items-center justify-content-center">
								    <a href="/product.do?command=detail-product&productId=${cart.productId}" class="d-flex align-items-center justify-content-center" style="text-decoration: none; color: inherit;">
								        <img src="${cart.thumbnail}" alt="상품 이미지" style="height: 150px;" />
								    </a>
								</td>
								<td style="width: 200px;">
								    <a href="/product.do?command=detail-product&productId=${cart.productId}" class="text-decoration-none text-dark">
								        ${cart.productName}
								    </a>
								</td>
								<td style="width: 80px;">${cart.sellerId}</td>
								<td class="align-items-center justify-content-center" >
				                    <div class="d-flex align-items-center justify-content-center" style="height: 100%;">
				                        <span id="amount-${cart.productId}" class="d-flex align-items-center justify-content-center">${cart.amount}</span>
				                        <div class="d-flex flex-column ms-2 justify-content-center align-items-center">
				                            <button type="button" id="${cart.productId}" class="btn btn-sm increase-btn">▲</button>
				                            <button type="button" id="${cart.productId}" class="btn btn-sm decrease-btn">▼</button>
				                        </div>
				                    </div>
				                </td>
								<td style="width: 400px;"></td>
								<td style="width: 150px;"><fmt:formatNumber value="${cart.price}" pattern="#,###" /></td>
								<td class="xButton d-flex align-center align-items-center" style="height: 168px">
									<button type="button" class="btn delete-btn btn-sm rounded-pill shadow-sm" data-product-id="${cart.productId}">
										<i class="bi bi-x"></i>
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
			            <tr class="totla-price align-middle">
			                <td colspan="6" class="text-end"><strong>총 가격:</strong></td>
			                <td>
			                    <span id="totalPrice">0</span>
			                </td>
			            </tr>
		        	</tfoot>
				</table>
				<div class="d-flex justify-content-end">
					<div class="d-flex w-25">
						<button type="button" id="compareBtn" class="btn btn-success btn-lg w-50 me-3">비교</button>
						<button type="button" id="buy-btn" class="btn btn-primary btn-lg w-50" >구매</button>
					</div>
				</div>
		</c:when>
            <c:otherwise>
                <div class="alert text-center mt-3" style="background-color: #e6f2ff; border-color: #bee5eb;">
                    	장바구니가 비어 있습니다! 새로운 물품을 둘러보세요!<br><br>
                    	<a href="/product.do?command=card-list-product" class="btn btn-primary btn-lg">지금 쇼핑하기</a>
                </div>
            </c:otherwise>
        </c:choose>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
	<%@include file="/WEB-INF/views/common/modal.jsp"%>
</body>
</html>
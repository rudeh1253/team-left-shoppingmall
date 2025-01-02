<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
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
	<form action="/purchase.do?command=purchase-product" method="POST">
		<h2>장바구니</h2>
		<c:choose>
            <c:when test="${not empty cartList}">
				<!-- 게시글 목록 -->
				<table class="table table-hover text-center mt-3 align-middle">
					<thead class="table-light">
						<tr>
							<th class="align-center" style="text-align: center;">이미지</th>
							<th>상품명</th>
							<th>판매자</th>
							<th>담은 날짜</th>
							<th>수량</th>
							<th></th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<c:forEach var="cart" items="${cartList}">
							<tr class="align-middle" data-product-id="${cart.productId}" data-price="${cart.price}" data-amount="${cart.amount}">
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
								<td style="width: 110px;">${cart.regdate}</td>
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
								<input type="hidden" name="memberId" value="${cart.memberId}" />
								<input type="hidden" name="sellerId" value="${cart.sellerId}" />
								<input type="hidden" name="productId" value="${cart.productId}" />
								<input type="hidden" name="amount" value="${cart.amount}" /> 
								<input type="hidden" name="price" value="${cart.price}" />
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
				<div class="text-end">
					<input type="submit" value="구매" class="btn btn-primary" />
				</div>
		</c:when>
            <c:otherwise>
                <div class="alert text-center mt-3" style="background-color: #e6f2ff; border-color: #bee5eb;">
                    	장바구니가 비어 있습니다! 새로운 물품을 둘러보세요!<br><br>
                    	<a href="/product.do?command=card-list-product" class="btn btn-primary btn-lg">지금 쇼핑하기</a>
                </div>
            </c:otherwise>
        </c:choose>
	</form>
	</div>

	<script>
		$(document).ready(function(e) {
			$(".increase-btn").on("click",
				function(e) {
					let requestUrl = "/cart.do?command=increase&productid="+ $(this).attr("id");
					const productId = $(this).attr("id");
					const amountElement = $("#amount-"+ productId);
					let currentAmount = parseInt(amountElement.text(),10); // 현재 수량
					currentAmount += 1; // 수량 증가
					amountElement.text(currentAmount);
					$("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
					calculateTotalPrice();
					$.ajax({
						url : requestUrl, // 서버에서 수량을 업데이트하는 URL
						type : 'GET'
					});
				});

				$(".decrease-btn").on("click",function(e) {
					const productId = $(this).attr("id");
				    const amountElement = $("#amount-" + productId);
				    let currentAmount = parseInt(amountElement.text(), 10);
				    currentAmount -= 1;
				    if (currentAmount <= 0) {
				        requestUrl = "/cart.do?command=delete&productid=" + productId;
				        alert("상품이 삭제되었습니다.");
				        $("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
					    calculateTotalPrice();
				        $.ajax({
				            url: requestUrl,
				            type: 'GET',
				            success: function () {
				                location.reload();
				            }
				        });
				    } else {
				        requestUrl = "/cart.do?command=decrease&productid=" + productId;
				        amountElement.text(currentAmount);
				        $("tr[data-product-id='" + productId + "']").attr("data-amount", currentAmount);
					    calculateTotalPrice();
				        $.ajax({
				            url: requestUrl,
				            type: 'GET'
				        });
				    }

				});
				
				$(".delete-btn").on("click", function() {
			        var productId = $(this).data("product-id");
			        let requestUrl = "/cart.do?command=delete&productid="+ $(this).attr("data-product-id");
			        $.ajax({
			            url: requestUrl,  // 요청을 보낼 URL
			            type: 'GET',
			            success: function(response) {
			                alert("상품이 삭제되었습니다.");
			                // 페이지 전체를 새로 고침하여 갱신
			                location.reload();
			            },
			            error: function(xhr, status, error) {
			                alert("삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
			            }
			        });
			        $("button[data-product-id='" + productId + "']").closest("tr").remove();
			    });
			});
		
		function calculateTotalPrice() {
	        let totalPrice = 0;
	        // 모든 상품을 순회하면서 가격과 수량을 곱한 값을 totalPrice에 더함
	        $("tr[data-price]").each(function() {
	            const price = parseFloat($(this).attr('data-price'));
	            const amount = parseInt($(this).attr('data-amount'));
	            totalPrice += price * amount;
	        });

	        // 총합을 화면에 출력 (천 단위 구분 기호 추가)
	        $('#totalPrice').text(totalPrice.toLocaleString());
	    }

	    // 페이지 로딩 시, 총합 계산
	    window.onload = calculateTotalPrice;
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
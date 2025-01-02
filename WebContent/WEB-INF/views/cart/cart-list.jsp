<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<div class="container mt-4" style="min-height: 76vh">
		<h2>장바구니</h2>
<form action="/purchase.do?command=purchase-product" method="POST">
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
							<img src="${cart.thumbnail}" alt="상품 이미지" style="height: 150px;"/>
						</td>
						<td style="width: 200px;">${cart.productName}</td>
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
			            type: 'GET'
			        });
			        alert("상품이 삭제되었습니다.");
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
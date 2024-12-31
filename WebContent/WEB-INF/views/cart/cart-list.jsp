<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container mt-4">
		<h2>게시판</h2>
		<div class="mb-3">
			<a href="write.jsp" class="btn btn-primary">글 작성</a>
		</div>

		<!-- 게시글 목록 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>썸네일</th>
					<th>상품ID</th>
					<th>개수</th>
					<th>판매자</th>
					<th>담은 날짜</th>
					<th>상품명</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="cart" items="${cartList}">
					<tr>
						<td><img src="${cart.thumbnail}" alt="상품 이미지" /></td>
						<td>${cart.productId}</td>
						<td>
							<button id="${cart.productId}" class="btn btn-sm increase-btn">▲</button>
							<span id="amount-${cart.productId}">${cart.amount}</span>
							<button id="${cart.productId}" class="btn btn-sm decrease-btn">▼</button>
						</td>
						<td>${cart.sellerId}</td>
						<td>${cart.regdate}</td>
						<td>${cart.productName}</td>
						<td>${cart.price}</td>
						<td>
							<button class="btn btn-sm delete-btn" data-product-id="${cart.productId}">X</button>
						</td>
					</tr>
					<form action="/purchase.do?command=purchase-test" method="POST">
						<input type="hidden" name="memberId" value="${cart.memberId}" />
						<input type="hidden" name="productId" value="${cart.productId}" />
						<input type="hidden" name="amount" value="${cart.amount}" /> 
						<input type="hidden" name="price" value="${cart.price}" />
				</c:forEach>
			</tbody>
		</table>
						<input type="submit" value="구매" />
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
					$.ajax({
						url : requestUrl, // 서버에서 수량을 업데이트하는 URL
						type : 'GET'
					});
				});

				$(".decrease-btn").on("click",function(e) {
					let requestUrl = "/cart.do?command=decrease&productid="+ $(this).attr("id");
					const productId = $(this).attr("id");
					const amountElement = $("#amount-"+ productId);
					let currentAmount = parseInt(amountElement.text(),10); // 현재 수량
					currentAmount -= 1; // 수량 증가
					amountElement.text(currentAmount);
					$.ajax({
						url : requestUrl, // 서버에서 수량을 업데이트하는 URL
						type : 'GET'
					});

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
	</script>
</body>
</html>
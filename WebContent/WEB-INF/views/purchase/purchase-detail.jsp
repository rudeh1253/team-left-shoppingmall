<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 정보</title>
</head>
<body>
	 <%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container py-5">
        <h1 class="mb-4">결제 상세 정보</h1>

        <div class="row">
            <!-- 상품 정보 섹션 -->
            <div class="col-md-6">
                <div class="card mb-4">
                    <div class="card-header bg-primary text-white">구매 정보</div>
                    <div class="card-body">
                        <div class="mb-3">
                            <strong>상품 이름</strong>
                            <p>${detailDto.productName}</p>
                        </div>
                        <div class="mb-3">
                            <strong>판매자 이름</strong>
                            <p>${detailDto.sellerName}</p>
                        </div>
                        <div class="mb-3">
                            <strong>구매 개수</strong>
                            <p>${detailDto.amount}개</p>
                        </div>
                        <div class="mb-3">
                            <strong>금액</strong>
                            <p>${detailDto.price}원</p>
                        </div>
                        <div class="mb-3">
                            <strong>구매자 이름</strong>
                            <p>${detailDto.buyerName}</p>
                        </div>
                    	<div class="mb-3">
                            <strong>구매일</strong>
                            <p>${detailDto.purchaseDate}</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 결제 정보 섹션 -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">배송 정보</div>
                    <div class="card-body">
                        <div class="mb-3">
                            <strong>배송 상태</strong>
                            <p>${detailDto.state}</p>
                        </div>
                        <div class="mb-3">
                            <strong>수취인 이름</strong>
                            <p>${detailDto.getterName}</p>
                        </div>
                        <div class="mb-3">
                            <strong>수취인 전화번호</strong>
                            <p>${detailDto.tel}</p>
                        </div>
                        <div class="mb-3">
                            <strong>배송 주소</strong>
                            <p>${detailDto.address}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <button id="check-btn" onclick="goToPurchaseList('${command}', '${sessionScope.member}')" class="btn btn-primary">확인</button>
        </div>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
    <script>
    	function goToPurchaseList(command, userid){
    		location.href="/purchase.do?command=" + command + "&userid=" + userid;
    	}
    </script>
</body>
</html>
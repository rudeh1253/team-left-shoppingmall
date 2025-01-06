<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제하기</title>
<script src="https://js.tosspayments.com/v1"></script>
</head>
<body>
    <%@include file="/WEB-INF/views/common/header.jsp"%>
    <div class="container py-5">
        <h1 class="mb-4 fw-bold">결제 페이지</h1>

        <!-- 배송 주소 확인 섹션 -->
        <form action="/purchase.do" method="POST" id="payment-form">
        <div class="card mb-4">
            <div class="card-header bg-primary text-white">
                배송 주소 확인
            </div>
            <div class="card-body">
            	<div class="mb-3">
                    <label for="price" class="form-label">총 결제 금액</label>
                    <div class="form-control" id="price">${shipInfo.totalPrice}</div>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">수령인 이름</label>
                    <input type="text" class="form-control" id="name" name="name" value="${shipInfo.name}" placeholder="수령인 이름을 입력하세요" />
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control" id="address" name="address" value="${shipInfo.address}" placeholder="주소를 입력하세요" />
                </div>
                <div class="mb-3">
                    <label for="tel" class="form-label">전화번호</label>
                    <input type="text" class="form-control" id="tel" name="tel" value="${shipInfo.tel}" placeholder="전화번호를 입력하세요" />
                </div>
            </div>
        </div>

        <!-- 결제 버튼 -->
        <div class="text-center mt-4">
        	<input type="hidden" name="command" value="purchase-process" />
            <button type="button" id="buy-complete-btn" class="btn btn-primary">결제하기</button>
        </div>
        </form>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>

    <script>
	    // TossPayments 객체를 생성합니다. (clientKey는 실제 발급 받은 클라이언트 키로 교체)
	    var tossPayments = TossPayments("test_ck_Gv6LjeKD8aNRyzAAX1vY3wYxAdXy");
	    
	    document.getElementById("buy-complete-btn").addEventListener("click", function(e) {

    	var name = $("#name").val();
    	var address = $("#address").val().replaceAll(" ", "@");
        var tel = $("#tel").val();
        var url = "http://localhost:8080/member.do?command=success" + "&name=" + name + "&address=" + address + "&tel=" + tel;
		console.log(url);
        
        // 결제 요청
        tossPayments.requestPayment('간편결제', {
            amount: $("#price").text(),
            orderId: "a4CWyWY5m89PNh7xJwhk1",
            orderName: $("#name").val() + "님의 장바구니",
            customerName: "none",
            customerEmail: "none",
            successUrl: url,
            failUrl: "http://localhost:8080/purchase.do?command=purchase-process" // 실패시 리디렉션 URL
        }).then(function(response) {
            // 결제 요청 후 응답 처리
            if (response.success) {
                alert("결제가 완료되었습니다.");
                // 결제 성공 후 추가적인 처리 로직을 여기에 추가할 수 있습니다.
            } else {
                alert("결제 실패. 다시 시도해 주세요.");
            }
        }).catch(function(error) {
            console.error(error);
            alert("결제 요청에 실패했습니다.");
        });
    });
        
    </script>
</body>
</html>

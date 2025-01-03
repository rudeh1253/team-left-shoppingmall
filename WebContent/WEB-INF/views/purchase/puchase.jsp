<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제하기</title>
</head>
<body>
	<div class="container py-5">
        <h1 class="mb-4">결제 페이지</h1>

        <!-- 배송 주소 확인 섹션 -->
        <div class="card mb-4">
            <div class="card-header bg-primary text-white">
                배송 주소 확인
            </div>
            <div class="card-body">
                <form>
                    <div class="mb-3">
                        <label for="recipientName" class="form-label">수령인 이름</label>
                        <input type="text" class="form-control" id="recipientName" placeholder="수령인 이름을 입력하세요">
                    </div>
                    <div class="mb-3">
                        <label for="recipientAddress" class="form-label">주소</label>
                        <input type="text" class="form-control" id="recipientAddress" placeholder="주소를 입력하세요">
                    </div>
                    <div class="mb-3">
                        <label for="recipientPhone" class="form-label">전화번호</label>
                        <input type="text" class="form-control" id="recipientPhone" placeholder="전화번호를 입력하세요">
                    </div>
                    <button type="button" class="btn btn-primary">주소 확인</button>
                </form>
            </div>
        </div>

        <!-- 무통장 입금 계좌 안내 섹션 -->
        <div class="card">
            <div class="card-header bg-success text-white">
                무통장 입금 계좌 안내
            </div>
            <div class="card-body">
                <p class="mb-2">아래 계좌로 입금해 주시면 확인 후 처리됩니다.</p>
                <ul class="list-group">
                    <li class="list-group-item">
                        <strong>은행명:</strong> 국민은행
                    </li>
                    <li class="list-group-item">
                        <strong>계좌번호:</strong> 123-456-7890
                    </li>
                    <li class="list-group-item">
                        <strong>예금주:</strong> 홍길동
                    </li>
                </ul>
            </div>
        </div>

        <!-- 결제 버튼 -->
        <div class="text-center mt-4">
            <button class="btn btn-success btn-lg">결제 완료</button>
        </div>
    </div>
</body>
</html>
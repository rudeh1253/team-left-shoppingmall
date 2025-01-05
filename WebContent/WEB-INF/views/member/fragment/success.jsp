<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>
<style>
    /* 전체 화면을 채우고 중앙 정렬 */
    body, html {
        height: 100%;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f0f4f7; /* 배경색을 설정 */
    }

    .wrapper {
        text-align: center;
    }

    .confirm-success {
        display: flex;
        flex-direction: column;
        align-items: center;
        max-width: 540px;
        width: 100%;
        padding: 20px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .confirm-success img {
        width: 120px;
        height: 120px;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
        color: #007bff;
        margin-top: 20px;
    }

    /* 메인으로 돌아가기 버튼 */
    .back-button {
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    .back-button:hover {
        background-color: #0056b3;
    }

    /* 폼 숨김 처리 */
    #payment-form {
        display: none;
    }
</style>
</head>
<body>

<%
    String tel = request.getParameter("tel");
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    request.setAttribute("tel", tel);
    request.setAttribute("name", name);
    request.setAttribute("address", address);
%>

<!-- 자동 제출 폼 -->
<form action="purchase.do" method="POST" id="payment-form">
    <input type="hidden" id="name" name="name" value="${name}">
    <input type="hidden" id="address" name="address" value="${address}">
    <input type="hidden" id="tel" name="tel" value="${tel}">
    <input type="hidden" name="command" value="purchase-product">
</form>

<!-- 결제 완료 화면 -->
<div class="wrapper">
    <div class="flex-column align-center confirm-success">
        <img src="https://static.toss.im/illusts/check-blue-spot-ending-frame.png" alt="결제 완료 아이콘">
        <h2 class="title">결제를 완료했어요</h2>
        <!-- 메인으로 돌아가기 버튼 -->
        <button class="back-button" onclick="window.location.href='/';">메인으로 돌아가기</button>
    </div>
</div>

<script>
    // 페이지 로드 시 폼 자동 제출
    window.onload = function() {
        const form = document.getElementById("payment-form");

        // 폼 데이터 유효성 확인
        const name = document.getElementById("name").value.trim();
        const address = document.getElementById("address").value.trim();
        const tel = document.getElementById("tel").value.trim();

        // 데이터가 모두 있는 경우에만 제출
        if (name && address && tel) {
            form.submit();
        } else {
            console.log("폼 데이터가 비어 있습니다. POST 요청이 취소되었습니다.");
        }
    };
</script>

</body>
</html>

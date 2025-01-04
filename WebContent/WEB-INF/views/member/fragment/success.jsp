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
</style>
</head>
<body>
    <div class="wrapper">
        <div class="flex-column align-center confirm-success">
            <img src="https://static.toss.im/illusts/check-blue-spot-ending-frame.png" alt="결제 완료 아이콘">
            <h2 class="title">결제를 완료했어요</h2>
            <!-- 메인으로 돌아가기 버튼 -->
            <button class="back-button" onclick="window.location.href='/';">메인으로 돌아가기</button>
        </div>
    </div>

    <script type="module" src="./success.js"></script>
</body>
</html>

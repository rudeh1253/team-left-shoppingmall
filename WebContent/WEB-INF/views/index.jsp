<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Left 쇼핑몰</title>
<style>
    .banner {
        position: relative;
        height: 70%;
        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;
    }

    .banner video {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: cover;
        z-index: -1; /* 콘텐츠 뒤로 이동 */
    }

    .banner::before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5); /* 어두운 투명 레이어 */
        z-index: 0;
    }

    .container {
        position: relative;
        z-index: 1; /* 비디오 위에 위치 */
        text-align: center;
        color: white;
    }

</style>
</head>
<body>
    <%@include file="/WEB-INF/views/common/header.jsp"%>
    <div class="banner">
        <video autoplay muted loop playsinline>
            <source src="/resources/images/bannerMovie.mp4" type="video/mp4">
            Your browser does not support the video tag.
        </video>
        <div class="container">
            <h1 class="mb-3 fw-bold">갤럭시와 애플, 당신이 원하는 스마트폰을 한곳에서!</h1>
            <p class="mb-4">최신 갤럭시와 아이폰을 비교하고, 최고의 가격으로 만나보세요.</p>
            <a href="/product.do?command=card-list-product" class="btn btn-primary btn-lg">지금 쇼핑하기</a>
        </div>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>

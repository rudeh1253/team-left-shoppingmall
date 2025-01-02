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
        background: url('/resources/images/banner3.png') no-repeat center;
        background-size : cover;
        height: 70%;
        display: flex;
        align-items: center;
        justify-content: center;
        
    }
    .banner::before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.2); /* 투명도 설정: 0.5 */
        z-index: 1;
    }

    .container {
        position: relative;
        z-index: 2; /* 콘텐츠는 배경 레이어 위에 위치 */
    }
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="banner">
	</br></br>
	<div class="container d-flex flex-column align-items-center justify-content-center" style="min-height: 76vh">
	    <div class="p-5 text-center">
	    </br></br>
	        <h1 class="mb-3 text-white">갤럭시와 애플, 당신이 원하는 스마트폰을 한곳에서!</h1>
	        <p class="mb-4 text-white">최신 갤럭시와 아이폰을 비교하고, 최고의 가격으로 만나보세요.</p>
	        <a href="/product.do?command=card-list-product" class="btn btn-primary btn-lg">지금 쇼핑하기</a>
	    </div>
	</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>


<!--
사이드 바 예시
<body>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%//@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center" style="flex: 1; padding: 1rem; overflow: auto; margin: 50px 0 100px 0">
			<div class="outer-box" style="padding: 0px; width: 600px">
				<div class="container">
					input data here 가운데 정렬은 이미 돼있음.
				</div>
			</div>
		</div>
	</div>
</body>

-->
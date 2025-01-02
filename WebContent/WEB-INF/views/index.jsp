<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Left 쇼핑몰</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="container d-flex flex-column align-items-center justify-content-center" style="height: 60vh;">
	    <div class="p-5 text-center">
	        <h1 class="mb-3">갤럭시와 애플, 당신이 원하는 스마트폰을 한곳에서!</h1>
	        <p class="mb-4 text-muted">최신 갤럭시와 아이폰을 비교하고, 최고의 가격으로 만나보세요.</p>
	        <a href="/product.do?command=list-product" class="btn btn-primary btn-lg">지금 쇼핑하기</a>
	    </div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>


<%
	//사이드바 @include file="/WEB-INF/views/common/sidebar.jsp"
%>
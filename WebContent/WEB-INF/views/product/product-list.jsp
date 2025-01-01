<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<p>리스트1</p><br><br>
		<p>리스트2</p><br><br>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            상품 등록하기
        </button>
	</div>
	<%@include file="/WEB-INF/views/product/productFormModal.jsp"%>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
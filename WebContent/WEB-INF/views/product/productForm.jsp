<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 등록</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
	        <div class="row">
	            <div class="col-5">
	                <img src="C:/dev/workspace/jsp-workspace/team-left-shoppingmall/WebContent/resources/images/NISI20240103_0001451604_web.jpg" class="img-fluid" alt="*">
	            </div>
	            <div class="col-7">
	            	<c:if test="${command eq 'add-product' }">
	            		<form action="/product.do?command=add-product" method="post">
	            	</c:if>
	            	<c:if test="${command eq 'edit-product' }">
	            		<form action="/product.do?command=edit-product" method="post">
	            	</c:if>
	                <!-- <form action="/product.do?command=add-product" method="post"> -->
	                    <div class="mb-3">
	                        <label for="productName" class="form-label fs-3">상품명</label>
	                        <input type="text" class="form-control" name="productName" id="productName" value="${product.productName }">
	                    </div>
	                    <div class="mb-3">
	                        <label for="price" class="form-label fs-3">가격</label>
	                        <input type="text" class="form-control" name="price" id="price" value="${product.price }">
	                    </div>
	                    <div class="mb-3">
	                        <label for="stock" class="form-label fs-3">재고</label>
	                        <input type="number" class="form-control" name="stock" id="stock" value="${product.stock }">
	                    </div>
	                    <div class="form-group mb-3">
	                        <label for="description" class="form-label fs-3">설명</label>
	                        <textarea class="form-control" id="description" name="description" rows="5">${product.description }</textarea>
	                    </div>
	                    <button type="submit" class="btn btn-primary btn-lg fs-5 w-25">
	                    	<c:if test="${command eq 'add-product' }">등록</c:if>
	                    	<c:if test="${command eq 'edit-product' }">수정</c:if>
	                    </button>
	                </form>
	            </div>
	        </div>
	    </div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
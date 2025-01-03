<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 비교</title>
<script src="/resources/js/product/product-compare.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container" style="min-height: 76vh">
		<div class="row align-items-center mb-4">
			<div class="col-md-4 text-center">
				<img src="${productList[0].thumbnail }" class="img-fluid" alt="상품 이미지">
			</div>
			<div class="col-md-4 text-center">
				<h1>VS</h1>
			</div>
			<div class="col-md-4 text-center">
				<img src="${productList[1].thumbnail }" class="img-fluid" alt="상품 이미지">
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 text-end">
				<ul class="list-group">
					<li class="list-group-item">${productList[0].productName }</li>
					<li class="list-group-item">${productList[0].sellerId }</li>
					<li class="list-group-item">${productList[0].price }</li>
					<li class="list-group-item">${productList[0].weight }</li>
					<li class="list-group-item">${productList[0].screenSize }</li>
					<li class="list-group-item">${productList[0].refreshRate }</li>
					<li class="list-group-item">${productList[0].displayResolution }</li>
					<li class="list-group-item">${productList[0].chipset }</li>
					<li class="list-group-item">${productList[0].cameraResolution }</li>
					<li class="list-group-item">${productList[0].batteryCapacity }</li>
				</ul>
			</div>

			<div class="col-md-4 text-center">
				<ul class="list-group">
					<li class="list-group-item bg-light"><strong>제품명</strong></li>
					<li class="list-group-item bg-light"><strong>판매자</strong></li>
					<li class="list-group-item bg-light"><strong>가격(원)</strong></li>
					<li class="list-group-item bg-light"><strong>무게(g)</strong></li>
					<li class="list-group-item bg-light"><strong>화면 크기(inch)</strong></li>
					<li class="list-group-item bg-light"><strong>주사율(Hz)</strong></li>
					<li class="list-group-item bg-light"><strong>화면 해상도</strong></li>
					<li class="list-group-item bg-light"><strong>칩셋</strong></li>
					<li class="list-group-item bg-light"><strong>카메라 해상도(pixel)</strong></li>
					<li class="list-group-item bg-light"><strong>배터리 용량(mAh)</strong></li>
				</ul>
			</div>

			<div class="col-md-4 text-start">
				<ul class="list-group">
					<li class="list-group-item">${productList[1].productName }</li>
					<li class="list-group-item">${productList[1].sellerId }</li>
					<li class="list-group-item">${productList[1].price }</li>
					<li class="list-group-item">${productList[1].weight }</li>
					<li class="list-group-item">${productList[1].screenSize }</li>
					<li class="list-group-item">${productList[1].refreshRate }</li>
					<li class="list-group-item">${productList[1].displayResolution }</li>
					<li class="list-group-item">${productList[1].chipset }</li>
					<li class="list-group-item">${productList[1].cameraResolution }</li>
					<li class="list-group-item">${productList[1].batteryCapacity }</li>
				</ul>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col text-center">
				<button type="button" id="back-btn" class="btn btn-secondary btn-lg">뒤로가기</button>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
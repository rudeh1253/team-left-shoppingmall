<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/product/product-form-modal.css">
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-2 ps-5 fw-bolder" id="staticBackdropLabel">상품 등록</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="p-5">
                	<form method="post" id="productModal"
	                	<c:if test="${isEdit }">
	                		action="/product.do?command=edit-product"
	                	</c:if>
	                	<c:if test="${!isEdit }">
	                		action="/product.do?command=add-product"
	                	</c:if>
                	>
	                    <div class="text-center">
	                        <img id="profile-image"
								<c:if test="${isEdit }">
									src="${product.thumbnail}" data-filename="${product.thumbnail}" data-has-changed="false"
								</c:if>
								<c:if test="${!isEdit }">
									src="/resources/images/default-product-image.png" data-filename="default-product-image.png"
								</c:if>            	
							alt="상품 이미지" class="img-fluid" />
	                        <input id="profile-image-file-select" type="file" accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
	                    </div>
	                    <input type="hidden" name="thumbnail" id="thumbnail">
                        <div class="row align-items-center mb-3">
                            <label for="productName" class="col-3 col-form-label fs-4 fw-bolder">상품명</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="productName" id="productName" value="${product.productName }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="price" class="col-3 col-form-label fs-4 fw-bolder">가격</label>
                            <div class="col-9">
                                <input type="number" class="form-control" name="price" id="price" min=0 value="${product.price }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="stock" class="col-3 col-form-label fs-4 fw-bolder">재고</label>
                            <div class="col-9">
                                <input type="number" class="form-control" name="stock" id="stock" min=0 value="${product.stock }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="weight" class="col-3 col-form-label fs-4 fw-bolder">무게(g)</label>
                            <div class="col-9">
                                <input type="number" class="form-control" name="weight" id="weight" min=0 value="${product.weight }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="screenSize" class="col-3 col-form-label fs-5 fw-bolder">화면 크기(inch)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="screenSize" id="screenSize" value="${product.screenSize }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="refreshRate" class="col-3 col-form-label fs-4 fw-bolder">주사율(Hz)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="refreshRate" id="refreshRate" value="${product.refreshRate }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="displayResolution" class="col-3 col-form-label fs-4 fw-bolder">화면 해상도</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="displayResolution" id="displayResolution" value="${product.displayResolution }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="chipset" class="col-3 col-form-label fs-4 fw-bolder">칩셋</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="chipset" id="chipset" value="${product.chipset }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="cameraResolution" class="col-3 col-form-label fs-5 fw-bolder">카메라 해상도(pixel)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="cameraResolution" id="cameraResolution" value="${product.cameraResolution }">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="batteryCapacity" class="col-3 col-form-label fs-5 fw-bolder">배터리 용량(mAh)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="batteryCapacity" id="batteryCapacity" value="${product.batteryCapacity }">
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <label for="description" class="form-label fs-3 fw-semibold">설명</label>
                            <textarea class="form-control" id="description" name="description" rows="5">${product.description }</textarea>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button id="submit-button" type="button" class="btn btn-primary" form="productModal" data-is-edit="${isEdit}">등록</button>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/product/product-form-modal.js"></script>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-2 ps-5 fw-bolder" id="staticBackdropLabel">상품 등록</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="p-5">
                    <div class="text-center my-5">
                        <!-- <img src="/NISI20240103_0001451604_web.jpg" class="img-fluid" alt="*"> -->
                    </div>
                    <form action="/product.do?command=add-product" method="post" id="insertProduct">
                        <div class="row align-items-center mb-3">
                            <label for="productName" class="col-3 col-form-label fs-4 fw-bolder">상품명</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="productName" id="productName">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="price" class="col-3 col-form-label fs-4 fw-bolder">가격</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="price" id="price">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="stock" class="col-3 col-form-label fs-4 fw-bolder">재고</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="stock" id="stock">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="weight" class="col-3 col-form-label fs-4 fw-bolder">무게(g)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="weight" id="weight">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="screenSize" class="col-3 col-form-label fs-5 fw-bolder">화면 크기(inch)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="screenSize" id="screenSize">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="refreshRate" class="col-3 col-form-label fs-4 fw-bolder">주사율(Hz)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="refreshRate" id="refreshRate">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="displayResolution" class="col-3 col-form-label fs-4 fw-bolder">화면 해상도</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="displayResolution" id="displayResolution">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="chipset" class="col-3 col-form-label fs-4 fw-bolder">칩셋</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="chipset" id="chipset">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="cameraResolution" class="col-3 col-form-label fs-5 fw-bolder">카메라 해상도(pixel)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="cameraResolution" id="cameraResolution">
                            </div>
                        </div>
                        <div class="row align-items-center mb-3">
                            <label for="batteryCapacity" class="col-3 col-form-label fs-5 fw-bolder">배터리 용량(mAh)</label>
                            <div class="col-9">
                                <input type="text" class="form-control" name="batteryCapacity" id="batteryCapacity">
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <label for="description" class="form-label fs-3 fw-semibold">설명</label>
                            <textarea class="form-control" id="description" name="description" rows="5"></textarea>
                        </div>
                        <!-- <div class="text-center">
                            <button type="submit" class="btn btn-primary btn-lg fs-5 w-25">등록</button>
                        </div> -->
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="submit" class="btn btn-primary" form="insertProduct">등록</button>
            </div>
        </div>
    </div>
</div>
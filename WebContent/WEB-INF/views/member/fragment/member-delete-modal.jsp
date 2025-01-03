<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="/resources/css/product/product-form-modal.css">

<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div style="width: 600px"
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-2 ps-3 fw-bolder" id="staticBackdropLabel">계정
					삭제</h1>
				<button type="button" class="btn-close me-2" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="p-3">
					<form action="/member.do?command=delete-member" method="post" id="modalform">
						<label class="fw-bolder text-danger" style="padding-bottom: 3px">
							비밀번호를 적고 확인버튼을 누르시면 계정이 삭제됩니다. </label>
						<div class="row align-items-center mb-3">
							<label for="productName"
								class="col-3 col-form-label fs-4 fw-bolder">비밀번호</label>
							<div class="col-9">
								<input type="text" class="form-control" name="password"
									id="password">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
							<input id="submit-button" type="submit"
								class="btn bg-danger text-light" form="modalform" value="제거">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function () {
    const truePassword = "${member.password}";
    $("#modalform").on("submit", function (event) {

        const passwordInput = $("#password").val().trim();
        console.log(passwordInput, truePassword);

        if (passwordInput !== truePassword) {
        	event.preventDefault();
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }
        
        this.submit();
    });
});
</script>

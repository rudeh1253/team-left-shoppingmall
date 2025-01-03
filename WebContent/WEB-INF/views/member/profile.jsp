<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="/resources/css/member/member-form.css">
<script src="/resources/js/member/profile.js"></script>
</head>
<body
	style="height: 100%; margin: 0; display: flex; flex-direction: column;">
	<%
		//@include file="/WEB-INF/views/common/header.jsp"
	%>
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center"
			style="flex: 1; padding: 1rem; overflow: auto; margin: 50px 0 100px 0">
			<div class="outer-box"
				style="padding: 0px; width: 600px; position: relative">
				<div class="page-title fw-bolder" style="padding: 50px">계정 보기</div>
				<form class="form-box" id="sign-up-form">
					<div class="input-box">
						<div class="profile-image-box">
							<img id="profile-image" src="${member.profile_img}"
								data-filename="default-profile-image.png"> <input
								id="profile-image-file-select" type="file"
								accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
						</div>
					</div>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">이메일 </label>
							<div class="horizontal-input-set">
								<span class="form-control form-input">${empty member.email ? 'NULL' : member.email}</span>
							</div>
						</div>
					</c:if>
					<div class="input-box">
						<label for="">이름</label> <span class="form-control form-input">${empty member.member_name ? 'NULL' : member.member_name}</span>
					</div>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">전화번호 </label> <span class="form-control form-input">${empty member.tel ? 'NULL' : member.tel}</span>

						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">주소 </label> <span class="form-control form-input">${empty member.address ? 'NULL' : member.address}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">포인트</label> <span class="form-control form-input">${empty member.point ? 'NULL' : member.point}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">생년월일 </label> <span class="form-control form-input">${empty member.birth_date ? 'NULL' : member.birth_date}</span>
						</div>
					</c:if>
					<div class="input-box">
						<label>성별 </label> <span class="form-control form-input">${member.gender == 'W' ? '여성' : '남성'}</span>
					</div>
					<div class="input-box" id="role-input-box">
						<label>역할 </label> <span class="form-control form-input">${member.role == "sell" ? "판매자" : "구매자"}</span>
					</div>
					<c:if test="${ !empty member.company }">
						<div class="input-box" id="company-input-box">
							<label for="">회사 </label> <span class="form-control form-input">${member.company}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">비밀번호 찾기 질문: 고향이 어디세요 </label> <span
								class="form-control form-input">${empty member.answer ? 'NULL' : member.answer}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">


						<div class="modal fade" id="password-change-modal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header" id="body-before">
										<h1 class="modal-title fs-5" id="exampleModalLabel">결과</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body" id="modal-content">
										<div style="margin-bottom: 28px">
											<input type="password" class="form-control" name="password"
												id="current-password-input" placeholder="현재 비밀번호를 입력하세요">
										</div>
										<div style="margin-bottom: 28px">
											<input type="password" class="form-control" name="password"
												id="new-password-input" placeholder="새 비밀번호를 입력하세요">
										</div>
										<div style="margin-bottom: 28px">
											<input type="password" class="form-control" name="password"
												id="new-password-check-input" placeholder="새 비밀번호를 다시 입력하세요">
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" id="password-change-submit-button"
											class="btn btn-primary">변경하기</button>
										<button type="button" id="modal-close-button"
											class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</form>
				<div class="d-flex w-100" style="margin-top:30px;">
					<button
						onclick="window.location.href='/member.do?command=edit-member'"
						style="height: 50px; padding: 5px; border: 0px; margin:0 30px"
						 class="rounded-3 pe-auto w-100 text-light fs-4 bg-primary fw-semibold" id="submit-button">수정하기</button>
					<button data-bs-target="#staticBackdrop" data-bs-toggle="modal"
						style="height: 50px; padding: 5px; border: 0px; margin:0 30px"
						class="bg-danger rounded-3 pe-auto w-100 text-light fs-4 fw-semibold">
						탈퇴하기</button>
				</div>
				<%@include file="/WEB-INF/views/common/footer.jsp"%>
			</div>
		</div>
	</div>
	<%@include
		file="/WEB-INF/views/member/fragment/member-delete-modal.jsp"%>
</body>
</html>
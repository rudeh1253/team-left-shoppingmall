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
			<div class="outer-box" style="padding: 0px; width: 600px">
				<div class="page-title fw-bolder" style="padding: 20px">계정 보기</div>
				<form class="form-box" id="sign-up-form">
					<div class="input-box">
						<label for="">프로필 사진</label>
						<div class="profile-image-box">
							<img id="profile-image" src="${member.profile_img}"
								data-filename="default-profile-image.png"> <input
								id="profile-image-file-select" type="file"
								accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
						</div>
					</div>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">이메일 
								<c:if test="${isMyProfile}">
									<label style="font-size: 12px">*수정 가능</label>
								</c:if>
							</label>
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
							<label for="">전화번호
								<c:if test="${isMyProfile}">
									<label style="font-size: 12px">*수정 가능</label>
								</c:if>
							</label> <span class="form-control form-input">${empty member.tel ? 'NULL' : member.tel}</span>
	
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">주소
								<c:if test="${isMyProfile}">
									<label style="font-size: 12px">*수정 가능</label>
								</c:if>
							</label> <span class="form-control form-input">${empty member.address ? 'NULL' : member.address}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">포인트</label> <span class="form-control form-input">${empty member.point ? 'NULL' : member.point}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">생년월일
								<c:if test="${isMyProfile}">
									<label style="font-size: 12px">*수정 가능</label>
								</c:if>
							</label> <span class="form-control form-input">${empty member.birth_date ? 'NULL' : member.birth_date}</span>
						</div>
					</c:if>
					<div class="input-box">
						<label>성별
							<c:if test="${isMyProfile}">
								<label style="font-size: 12px">*수정 가능</label>
							</c:if>
						</label> <span
							class="form-control form-input">${empty member.gender ? 'NULL' : member.gender}</span>
					</div>
					<div class="input-box" id="role-input-box">
						<label>역할
							<c:if test="${isMyProfile}">
								<label style="font-size: 12px">*수정 가능</label>
							</c:if>
						</label> <span
							class="form-control form-input">${empty member.role ? 'NULL' : member.role}</span>
					</div>
					<div class="input-box" id="company-input-box">
						<label for="">회사
							<c:if test="${isMyProfile}">
								<label style="font-size: 12px">*수정 가능</label>
							</c:if>
						</label> <span class="form-control form-input">${empty member.company ? "NULL" : member.company}</span>
					</div>
					<c:if test="${isMyProfile}">
						<div class="input-box">
							<label for="">비밀번호 찾기 질문: 고향이 어디세요
								<c:if test="${isMyProfile}">
									<label style="font-size: 12px">* 수정 가능</label>
								</c:if>
							</label> <span
								class="form-control form-input">${empty member.answer ? 'NULL' : member.answer}</span>
						</div>
					</c:if>
					<c:if test="${isMyProfile}">
						<button
							onclick="window.location.href='/member.do?command=edit-member'"
							style="" type="button" class="submit-button" id="submit-button">수정하기</button>
						<button class="submit-button"
						        type="button"
						        data-bs-toggle="modal"
						        data-bs-target="#password-change-modal"
						        id="password-change-modal-trigger-button">비밀번호 변경</button>
						<div class="modal fade" id="password-change-modal" tabindex="-1"
							 aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header" id="body-before">
										<h1 class="modal-title fs-5" id="exampleModalLabel">결과</h1>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body" id="modal-content">
										<div style="margin-bottom: 28px">
											<input type="password"
											       class="form-control"
											       name="password"
											       id="current-password-input"
											       placeholder="현재 비밀번호를 입력하세요">
										</div>
										<div style="margin-bottom: 28px">
											<input type="password"
											       class="form-control"
											       name="password"
											       id="new-password-input"
											       placeholder="새 비밀번호를 입력하세요">
										</div>
										<div style="margin-bottom: 28px">
											<input type="password"
											       class="form-control"
											       name="password"
											       id="new-password-check-input"
											       placeholder="새 비밀번호를 다시 입력하세요">
										</div>
									</div>
									<div class="modal-footer">
										<button type="button"
												id="password-change-submit-button"
										        class="btn btn-primary">변경하기</button>
										<button type="button"
												id="modal-close-button"
										        class="btn btn-secondary"
											    data-bs-dismiss="modal">닫기</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<%@include file="/WEB-INF/views/common/footer.jsp"%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="/resources/css/member/member-form.css">

<script src="/resources/js/member/member-form/birth-date-input.js"></script>
<script src="/resources/js/member/member-form/validation.js"></script>
<script src="/resources/js/member/member-form/submit.js"></script>
<script src="/resources/js/member/member-form/form-event.js"></script>
<script src="/resources/js/member/member-form/member-form.js"></script>

<style>

.innerImg{
	width: 200px !important;
	height: 360px !important;
}

</style>

</head>
<c:set var="isEdit" value="${empty command || command == 'edit-member'}" />
<body data-is-edit="${isEdit}">
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="outer-box">
		<div class="page-title" style="margin: 10 0 60 0">
			<c:if test="${isEdit}">
				프로필 수정
			</c:if>
			<c:if test="${!isEdit}">
				계정 생성
			</c:if>
		</div>
		<form class="form-box" id="sign-up-form"
		      <c:if test="${isEdit}">
		          action="/member.do?command=edit-member"
		      </c:if>
		      <c:if test="${!isEdit}">
		          action="/member.do?command=insert-member"
		      </c:if>
		      method="POST">
			<div class="input-box">
				<div class="profile-image-box">
					<img id="profile-image" class="innerImg"
					     <c:if test="${isEdit}">
					     	src="${profileImage}" data-filename="${profileImageFileName}" data-has-changed="false"
					     </c:if>
					     <c:if test="${!isEdit}">
					     	src="/resources/images/default-profile-image.png" data-filename="default-profile-image.png"
					     </c:if>
					>
					<input id="profile-image-file-select" type="file" accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
					
				</div>
				<br/>
				<label class="text-center" for="">프로필 사진</label>
				<label class="text-center"  for=""> ★ 사진 해상도는 720px x 480px으로 추천 드립니다.★ </label>
			</div>
			<div class="input-box">
				<label for="">이메일</label>
				<div class="horizontal-input-set">
					<div class="form-input-wrapper">
						<input class="form-control form-input"
						       type="text"
						       name="email-account"
						       value="${emailAccount}"
						       <c:if test="${isEdit}">
						           disabled
						       </c:if>
						>
					</div>
					<p>@</p>
					<div class="form-input-wrapper">
						<input class="form-control form-input"
						       type="text"
						       name="email-host"
						       value="${emailHost}"
						       <c:if test="${isEdit}">
						           disabled
						       </c:if>
						>
					</div>
				</div>
			</div>
			<c:if test="${!isEdit}">
				<div class="input-box">
					<label for="">비밀번호</label>
					<div class="form-input-wrapper">
						<input class="form-control form-input" type="password" name="password" required>
					</div>
				</div>
				<div class="input-box">
					<label for="">비밀번호 확인</label>
					<div class="form-input-wrapper">
						<input class="form-control form-input" type="password" name="password-check" required>
					</div>
				</div>
			</c:if>
			<c:if test="${isEdit}">
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
								<button type="button" class="btn-close"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body" id="modal-content">
								<div style="margin-bottom: 28px">
									<input type="password" class="form-control" name="current-password"
										id="current-password-input" placeholder="현재 비밀번호를 입력하세요">
								</div>
								<div style="margin-bottom: 28px">
									<input type="password" class="form-control" name="new-password"
										id="new-password-input" placeholder="새 비밀번호를 입력하세요">
								</div>
								<div style="margin-bottom: 28px">
									<input type="password" class="form-control" name="new-password-check"
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
			<div class="input-box">
				<label for="">이름</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input"
					       type="text"
					       name="member-name"
					       value="${memberName}">
				</div>
			</div>
			<div class="input-box">
				<label for="">전화번호</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input"
					       type="text"
					       name="tel"
					       value="${tel}">
				</div>
			</div>
			<div class="input-box">
				<label for="">주소</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input"
					       type="text"
					       name="address"
					       value="${address}">
				</div>
			</div>
			<div class="input-box">
				<label for="">생년월일</label>
				<div class="form-control horizontal-input-set">
					<select class="form-select" id="year-select" data-year="${year}">
					</select>
					<p class="date-label">년</p>
					<select class="form-select" id="month-select" data-month="${month}">
					</select>
					<p class="date-label">월</p>
					<select class="form-select" id="day-of-month-select" data-day-of-month="${dayOfMonth}">
					</select>
					<p class="date-label">일</p>
				</div>
			</div>
			<div class="input-box">
				<label>성별</label>
				<div class="radio-box" class="form-check">
					<div class="radio-item">
						<label class="form-check-label">남</label>
						<input class="form-check-input"
						       type="radio"
						       name="gender"
						       <c:if test="${empty gender || gender == 'M'}">
						           checked
						       </c:if>
						       value="M">
					</div>
					<div class="radio-item">
						<label class="form-check-label">여</label>
						<input class="form-check-input"
						       type="radio"
						       name="gender"
						       <c:if test="${!empty gender || gender == 'W'}">
						           checked
						       </c:if>
						       value="W">
					</div>
				</div>
			</div>
			<div class="input-box" id="role-input-box">
				<label>역할</label>
				<div class="radio-box" class="form-check">
					<div class="radio-item">
						<label class="form-check-label">판매자</label>
						<input class="form-check-input"
						       id="seller-select"
						       type="radio"
						       name="role"
						       value="sell"
						       data-company="${company}"
						       <c:if test="${empty role || role == 'sell'}">
						           checked
						       </c:if>
						       >
					</div>
					<div class="radio-item">
						<label class="form-check-label">구매자</label>
						<input class="form-check-input"
						       type="radio"
						       name="role"
						       value="buy"
						       <c:if test="${not empty role && role == 'buy'}">
						           checked
						       </c:if>
						       >
					</div>
				</div>
			</div>
			<div class="input-box" id="company-input-box">
                <label for="">회사</label>
                <div class="form-input-wrapper">
                    <input class="form-control form-input" type="text" name="company" value="${company}" required>
                </div>
            </div>
			<div class="input-box">
				<label for="">비밀번호 찾기 질문: 고향이 어디세요</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input" type="text" name="answer" value="${answer}">
				</div>
			</div>
			<button type="button" class="submit-button" id="submit-button" data-is-edit="${isEdit}">
				<c:if test="${isEdit}">
					수정 완료
				</c:if>
				<c:if test="${!isEdit}">
					회원가입
				</c:if>
			</button>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
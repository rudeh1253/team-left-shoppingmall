<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/css/member/member-form.css">

<script src="/resources/js/member/member-form/birth-date-input.js"></script>
<script src="/resources/js/member/member-form/validation.js"></script>
<script src="/resources/js/member/member-form/submit.js"></script>
<script src="/resources/js/member/member-form/form-event.js"></script>
<script src="/resources/js/member/member-form/member-form.js"></script>

</head>
<body>
	<div class="outer-box">
		<div class="page-title">계정 생성</div>
		<form action="/member.do?command=insert-member" method="POST" class="form-box" id="sign-up-form">
			<div class="input-box">
				<label for="">프로필 사진</label>
				<div class="profile-image-box">
					<img id="profile-image" src="/resources/images/default-profile-image.png" data-filename="default-profile-image.png">
					<input id="profile-image-file-select" type="file" accept=".jpg,.png,.jpeg,.gif,.webp,.bmp">
				</div>
			</div>
			<div class="input-box">
				<label for="">이메일</label>
				<div class="horizontal-input-set">
					<div class="form-input-wrapper">
						<input class="form-control form-input" type="text" name="email-account" required>
					</div>
					<p>@</p>
					<div class="form-input-wrapper">
						<input class="form-control form-input" type="text" name="email-host" required>
					</div>
				</div>
			</div>
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
			<div class="input-box">
				<label for="">이름</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input" type="text" name="member-name" required>
				</div>
			</div>
			<div class="input-box">
				<label for="">전화번호</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input" type="text" name="tel" required>
				</div>
			</div>
			<div class="input-box">
				<label for="">주소</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input" type="text" name="address" required>
				</div>
			</div>
			<div class="input-box">
				<label for="">생년월일</label>
				<div class="form-control horizontal-input-set">
					<select class="form-select" id="year-select">
					</select>
					<p class="date-label">년</p>
					<select class="form-select" id="month-select">
					</select>
					<p class="date-label">월</p>
					<select class="form-select" id="day-of-month-select">
					</select>
					<p class="date-label">일</p>
				</div>
			</div>
			<div class="input-box">
				<label>성별</label>
				<div class="radio-box" class="form-check">
					<div class="radio-item">
						<label class="form-check-label">남</label>
						<input class="form-check-input" type="radio" name="gender" value="M" checked>
					</div>
					<div class="radio-item">
						<label class="form-check-label">여</label>
						<input class="form-check-input" type="radio" name="gender" value="W">
					</div>
				</div>
			</div>
			<div class="input-box" id="role-input-box">
				<label>역할</label>
				<div class="radio-box" class="form-check">
					<div class="radio-item">
						<label class="form-check-label">판매자</label>
						<input class="form-check-input" type="radio" name="role" value="sell" checked>
					</div>
					<div class="radio-item">
						<label class="form-check-label">구매자</label>
						<input class="form-check-input" type="radio" name="role" value="buy">
					</div>
				</div>
			</div>
			<div class="input-box" id="company-input-box">
                <label for="">회사</label>
                <div class="form-input-wrapper">
                    <input class="form-control form-input" type="text" name="company" required>
                </div>
            </div>
			<div class="input-box">
				<label for="">비밀번호 찾기 질문: 후배가 마라탕후루 사달라고 하면 뭐라고 하실 겁니까?</label>
				<div class="form-input-wrapper">
					<input class="form-control form-input" type="text" name="answer" required>
				</div>
			</div>
			<button type="button" id="submit-button">회원가입</button>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/css/member-form.css">

<script src="/resources/js/member/member-form/birth-date-input.js"></script>
<script src="/resources/js/member/member-form/member-form.js"></script>

</head>
<body>
	<div class="outer-box">
		<div class="page-title">계정 생성</div>
		<form action="/member.do?command=insert-member" method="POST" class="form-box">
			<div class="input-box">
				<label for="">이메일</label>
				<div class="horizontal-input-set">
					<input class="form-input" type="text" name="{email}" required>
					<p>@</p>
					<input class="form-input" type="text">
				</div>
			</div>
			<div class="input-box">
				<label for="">비밀번호</label>
				<input class="form-input" type="password" name="{password}" required>
			</div>
			<div class="input-box">
				<label for="">비밀번호 확인</label>
				<input class="form-input" type="password" name="{password_check}" required>
			</div>
			<div class="input-box">
				<label for="">이름</label>
				<input class="form-input" type="text" name="{name}" required>
			</div>
			<div class="input-box">
				<label for="">전화번호</label>
				<input class="form-input" type="text" name="{phoneNum}" required>
			</div>
			<div class="input-box">
				<label for="">주소</label>
				<input class="form-input" type="text" name="{address}" required>
			</div>
			<div class="input-box">
				<label for="">생년월일</label>
				<div class="horizontal-input-set">
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
						<input class="form-check-input" type="radio" name="sex" value="{male}" checked>
					</div>
					<div class="radio-item">
						<label class="form-check-label">여</label>
						<input class="form-check-input" type="radio" name="sex" value="{female}">
					</div>
				</div>
			</div>
			<input type="submit" value="회원가입" />
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/member/login.css">

<script src="/resources/js/member/login/login.js"></script>
</head>
<body>
	<div class="outer-box">
		<form action="/member.do?command=login" method="POST" id="login-form">
			<div class="login-form-box">
				<div class="form-input-box-set">
					<div class="form-input-box">
						<input type="email" class="form-control form-input" id="email-input" name="email" placeholder="이메일">
					</div>
					<div class="form-input-box">
						<input type="password" class="form-control form-input" id="password-input" name="password" placeholder="패스워드">
					</div>
				</div>
				<div>
					<button class="login-button" type="button" id="login-btn">로그인</button>
				</div>
			</div>
		</form>
		<div class="navigation-box">
			<div>
				<a href="/member.do?command=insert-member">회원가입</a>
			</div>
			<p>|</p>
			<div>
				<a href="/member.do?command=find-id">아이디 찾기</a>
			</div>
			<p>|</p>
			<div>
				<a href="/member.do?command=find-password">비밀번호 찾기</a>
			</div>
		</div>
	</div>
</body>
</html>
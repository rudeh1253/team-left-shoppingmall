<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/member/login-find-id-pw.css">

<script src="/resources/js/member/login/login.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
	    <div class="login-container mt-5">
	      <form action="/member.do?command=login" method="POST">
		      <fieldset>
		      	<legend class="text-center mb-4" style="font-size: 24px;">로그인</legend>
		      	<div class="mb-3">
		          <input type="email" class="form-control" name="email" placeholder="이메일을 입력하세요">
		        </div>
		        <div class="mb-3">
		          <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
		        </div>
		        <button type="submit" class="btn btn-custom w-100">로그인</button>
	      	</fieldset>
	      </form>
	    </div>
	    <div class="navigation-box mb-5">
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
  <%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
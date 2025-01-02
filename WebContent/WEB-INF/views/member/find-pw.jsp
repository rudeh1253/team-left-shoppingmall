<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="/resources/css/member/login-find-id-pw.css">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
	    <div class="login-container">
	      <form action="/member.do?command=find-password" method="POST">
		      <fieldset>
		      	<legend class="text-center mb-4" style="font-size: 24px;">비밀번호 찾기</legend>
		      	<div class="mb-3">
		          <input type="email" class="form-control" name="email" placeholder="이메일을 입력하세요">
		        </div>
		        <div class="mb-3">
		          <input type="text" class="form-control" name="tel" placeholder="전화번호를 입력하세요 (ex: 010-1111-1111)">
		        </div>
		        <button type="submit" class="btn btn-custom w-100">제출</button>
	      	</fieldset>
	      </form>
	    </div>
	  </div>
	  <%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
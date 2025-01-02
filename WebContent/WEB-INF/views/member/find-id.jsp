<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="/resources/css/member/login-find-id-pw.css">
<script src="/resources/js/member/find-id.js"></script>
</head>
<body data-command="find-id">
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
	    <div class="login-container">
	      <form action="/member.do?command=find-id" method="POST">
		      <fieldset>
		      	<legend class="text-center mb-4" style="font-size: 24px;">아이디 찾기</legend>
		      	<div class="mb-3">
		          <input type="text" class="form-control" name="name" placeholder="이름을 입력하세요">
		        </div>
		        <div class="mb-3">
		          <input type="text" class="form-control" name="tel" placeholder="전화번호를 입력하세요 (ex: 010-1111-1111)">
		        </div>
		        <button type="button"
		                class="btn btn-custom w-100"
		                id="submit-button"
		        >제출</button>
	      	</fieldset>
	      </form>
	    </div>
	</div>
	<input id="modal-trigger" type="hidden" data-bs-toggle="modal" data-bs-target="#result-modal">
	<%@include file="fragment/find-id-pw-modal.jsp"%>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-body" id="modal-content">
	<p>
		<c:if test="${exists}">
			${which}:
		</c:if>
		<strong>${result}</strong>
	</p>
</div>
<div class="modal-footer">
	<c:if test="${exists}">
		<button type="button"
		        class="btn btn-primary"
		        onClick="location.href = '/member.do?command=login'"
			    data-bs-dismiss="modal">로그인</button>
	</c:if>
	<c:if test="${!exists}">
		<button id="to-login-button" type="button" class="btn btn-secondary"
			data-bs-dismiss="modal">닫기</button>
	</c:if>
</div>
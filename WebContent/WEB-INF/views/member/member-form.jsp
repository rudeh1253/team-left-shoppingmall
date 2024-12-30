<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value='/member.do?command=insert-member' />" method="POST">
	이메일: <input type="text" name="email">
	패스워드: <input type="password" name="password">
	회원 이름: <input type="text" name="memberName">
	생년월일: <input type="number" name="year">년 <input type="number"  name="month">월 <input type="number" name="dayOfMonth">일
	전화번호: <input type="text" name="tel">
	주소: <input type="text" name="address">
	성별:
	<select name="gender">
		<option value="M">남</option>
		<option value="W">여</option>
	</select>
	<input type="radio" name="role" value="sell"> 판매자 <input type="radio" name="role" value="buy"> 구매자
	회사 <input type="text" name="company">
	질문: 뭐하고 사니
	답변: <input type="text" name="answer">

	<input type="submit" value="제출">
</form>
</body>
</html>
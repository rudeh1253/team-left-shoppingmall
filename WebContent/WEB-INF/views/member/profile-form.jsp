<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member.do?command=edit-member" method="POST">
	이름 <input type="text" name="member-name">
	이메일 <input type="text" name="email">
	주소 <input type="text" name="address">
	생년월일: <input type="number" name="year">년 <input type="number" name="month">월 <input type="number" name="day-of-month">일
	전화번호: <input type="text" name="tel">
	성별: <input type="radio" name="gender"> 남 <input type="radio" name="gender"> 여
	<input type="submit" value="제출">
	<input type="reset" value="취소">
</form>
</body>
</html>
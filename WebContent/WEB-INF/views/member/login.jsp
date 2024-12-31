<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member.do?command=login" method="POST">
		<input type="text" name="email"> <input type="password"
			name="password"> <input type="submit" value="제출">
	</form>
</body>
</html>
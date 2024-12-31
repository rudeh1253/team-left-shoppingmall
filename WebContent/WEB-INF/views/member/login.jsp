<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<form action="<%= request.getContextPath() %>/member.do?command=login" method="POST">
	이메일 <input type="text" name="email">
	패스워드 <input type="password" name="password">
	<input type="submit" value="제출">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String type = (String) session.getAttribute("type");
	String data = (String) session.getAttribute("result");
	String text = "";

	if (!data.equals("")) {
		if (type.equals("id")) {
			text = "검색된 아이디 : " + data;
		} else if (type.equals("password")) {
			text = "검색된 비밀번호" + data;
		}
	} else { text = " 데이터가 존재하지 않습니다. "; }
	session.removeAttribute("type");
	session.removeAttribute("result");

	request.setAttribute("text", text);
	%>

	<div>${ text }</div>
</body>
</html>
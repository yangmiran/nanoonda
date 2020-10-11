<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_ERRORPAGE</title>
</head>
<body>
	<%-- <%@ include file="./header.jsp" %> --%>
	<h1>에러 페이지</h1>
	<% if(exception != null){ //다른 jsp 페이지에서 오류가 발생했을 때 %>
		<h3>jsp 페이지 오류 발생 : <%= exception.getMessage() %></h3>
	<% }else{ //서블릿에서 포워딩한 오류 메세지일 때 %>
		<h3>Servlet 메시지 : <%= request.getAttribute("message") %></h3>
	<% } %>
	<br>
	<a href="/nnd/index.jsp">시작페이지로 이동</a>
</body>
</html>
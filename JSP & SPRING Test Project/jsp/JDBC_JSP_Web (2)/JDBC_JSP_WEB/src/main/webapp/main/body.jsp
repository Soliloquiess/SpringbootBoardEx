<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="head.jsp" %> --%>
<% String name = "이영환"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="head.jspf" %>
<p style="border: 1px solid #444;">
내용 부분입니다.<br>
내용 부분입니다.<br>
내용 부분입니다.<br>
<%= name %><br>
</p>
<%@ include file="footer.jspf" %>
</body>
</html>
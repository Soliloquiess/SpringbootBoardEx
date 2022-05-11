<%@page import="com.board.service.BoardDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//한글처리
Class.forName("com.util.db.DB");
request.setCharacterEncoding("utf-8");
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

//생성하고 호출 BoardDeleteService
BoardDeleteService service = new BoardDeleteService();
int result = service.service(no);

//자동으로 list로 보낸다.
response.sendRedirect("list.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=no %>
</body>
</html>
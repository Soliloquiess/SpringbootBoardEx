<%@page import="com.board.service.BoardDeleteService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

//�����ϰ� ȣ�� BoardDeleteService
BoardDeleteService service = new BoardDeleteService();
int result = service.service(no);

//�ڵ����� list�� ������.
response.sendRedirect("list.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%=no %>
</body>
</html>
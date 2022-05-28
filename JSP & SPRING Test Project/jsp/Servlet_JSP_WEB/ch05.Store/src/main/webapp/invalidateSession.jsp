<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Data setting</title>
</head>
<body>
<h2>data EL을 이용한 데이터 출력</h2>
pageContext -> request -> session -> application<br>
Application data : ${data }<br>
Session data : ${data }<br>
Request data : ${data }<br>
PageContext data : ${data }<br>

<h2>data EL을 이용한 scope(영역) 데이터 출력</h2>
찾는 우선 순위 : pageContext -> request -> session -> application<br>
Application data : ${applicationScope.data }<br>
Session data : ${sessionScope.data }<br>
Session ID : <%= session.getId() %><br>
Request data : ${requestScope.data }<br>
PageContext data : ${pageScope.data }<br>
<a href="nextInvalidateSession.jsp">nextInvalidateSession.jsp</a>
</body>
</html>

<%
// 현재 사용자의 session을 없애는 처리
// 처리를 하면 session에 대해 전혀 사용 불가가되서 처리문 이후의 session 사용을 오류가 난다. 그래서 맨뒤에 처리한다.
session.invalidate();
%>
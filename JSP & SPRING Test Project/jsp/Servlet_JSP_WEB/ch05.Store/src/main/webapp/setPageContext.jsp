<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 페이지를 이동하면 데이터가 사라진다.
// 브라우저를 껐다가 키면 데이터가 사라진다.
pageContext.setAttribute("data", "page data");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request Data setting</title>
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
Request data : ${requestScope.data }<br>
PageContext data : ${pageScope.data }<br>
<a href="nextSetPageContext.jsp">nextSetPageContext.jsp</a>
</body>
</html>
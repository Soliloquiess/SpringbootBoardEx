<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 페이지를 이동하면 데이터가 유지된다.
// 브라우저를 껐다가 키면 데이터가 사라진다.
//원래 쿠키에다 저장했었는데(브라우저 껐다 키면 자동 로그인 됨. 쿠키에 저장해뒀다가 알아서 쿠키가 들고와서 했던거. 그래서 쿠키에 설정시간 뒀던가. 근데 해킹 워낙 많이 당해서 안 씀.)
session.setAttribute("data", "Session data");
%>

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
Request data : ${requestScope.data }<br>
PageContext data : ${pageScope.data }<br>
<a href="nextSetSession.jsp">nextSetSession.jsp</a>
</body>
</html>
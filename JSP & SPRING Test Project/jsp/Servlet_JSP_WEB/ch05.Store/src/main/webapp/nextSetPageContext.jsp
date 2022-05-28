<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<a href="removeSessionData.jsp">removeSessionData.jsp</a>
</body>
</html>
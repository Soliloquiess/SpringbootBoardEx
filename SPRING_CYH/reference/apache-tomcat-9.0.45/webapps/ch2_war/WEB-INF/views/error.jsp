<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%>
<!-- isErrorPage="true" 이걸 넣어주면 이페이지는 에러가 날 떄 보여주는 페이지라는 걸 명시 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>
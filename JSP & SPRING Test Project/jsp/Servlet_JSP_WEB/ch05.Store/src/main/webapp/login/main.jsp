<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인페이지</h1>
	<!-- session.login -LoginVO객체에 데이터를 담아서 저장한다. -->
	<!-- 로그인 하지 않은 경우 -->
	<c:if test="${ empty login}">
		<a href="loginForm.jsp"> 로그인 </a>
	</c:if>

<!-- 로그인 한 경우의 메뉴 -->
	<c:if test="${!empty login}">
		<a href="logout.jsp"> 로그아웃 </a>
		<c:if test = "${login.gradeNo==9 }">
		<a href = "/member/list.jsp">회원관리</a>
		</c:if>
	</c:if>
	
</body>
</html>
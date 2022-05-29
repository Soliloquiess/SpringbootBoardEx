<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Main Page</h1>
	<!--  일반 사용자 : 로그인이 안 된 상태 -->
	<c:if test = "${empty login }">
	<a href = "/member/login.jsp">로그인</a>
	<a href = "/member/clouse.jsp">회원가입</a>
	</c:if>
	
	<!-- 일반 사용자: 로그인이 안 된 상태 -->
	<a href = "/member/logout.jsp">로그아웃</a>
	<a href = "/member/view.jsp">내 정보 보기</a>
	<!-- 관리자 : 로그인 된 상태 -->
	<c:if test= "${login.gradeNo ==9}">
		<a href = "/member/list.jsp" class= "btn btn-default">회원 리스트</a>
	</c:if>
</body>
</html>
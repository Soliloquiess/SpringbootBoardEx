<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1,div{border:1px solid #888;}
</style>
</head>
<body>
	<h1>전체 레이아웃</h1>
	<div>
		사이트 
		
		<a href="#">게시판</a> 
		<a href="#">공지사항</a> 
		<a href="#">이미지</a>

	</div>
<div>

<!--  crud 프로그램이 포함되는 곳 :include action tag-->

<jsp:include page = "list.jsp"></jsp:include>
</div>

<div>

커뮤니티 copyright 2022.05.31
</div>

</body>
</html>
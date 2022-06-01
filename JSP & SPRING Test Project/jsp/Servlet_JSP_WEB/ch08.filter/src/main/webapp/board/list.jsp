<%@page import="com.board.service.BoardListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.main.controller.ExecuteService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setAttribute("list", ExecuteService.execute(new BoardListService(), null));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>게시판 리스트</title>
<!-- bootstrap 라이브러리 cdn 방식 등록 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- 스타일 타입 태그 안에 주석 넣으면 안먹는다 주의 -->
<style type="text/css">
.dataRow:hover{
	background:#eee;
	cursor : pointer;
}
</style>

<script type="text/javascript">
// HTML 부분이 로딩이 다되면 실행된다.
$(function(){
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		location = "view.jsp?no=" + no + "&inc=1";
	});
});
</script>

</head>
<body>
	<div class="container">
		<h1>게시판 리스트</h1>
		<%-- ${list} --%>
		<!--html tag안에 클래스를 활용한 디자인:bootstrap -->
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>

			</tr>
			<!-- jstl if문 test = 조건 . empty list - > jstl el : list 가 널이거나 list.size() ==0 -->
			<c:if test="${empty list}">
					<tr>
						<td colspan="5">데이터가 존재하지 않습니다.</td>
					</tr>
			</c:if>
			
			<c:if test="${!empty list}">
				<c:forEach 	items= "${list}" var = "vo">
				<!--  tr객체를 쉽게 선택할 수 있도록  하기 위한 class 지정 -->
					<tr class = "dataRow">
						<td class="no">${vo.no}</td>
						<td>${vo.title}</td>
						<td>${vo.writer}</td>
						<td>${vo.writeDate}</td>
						<td>${vo.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<a href = "write.jsp" class = "btn btn-default">글쓰기</a>
	</div>
</body>
</html>
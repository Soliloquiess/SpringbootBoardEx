<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.board.service.BoardListService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/board_err.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<% 
// DB에 관련 확인
Class.forName("com.webjjang.util.db.DB");

// 페이지 정보 받기
PageObject pageObject = PageObject.getInstance(request);

// DB에서 데이터 가져오기
BoardListService service = new BoardListService();
List<BoardVO> list = service.service(pageObject);
// System.out.println(10/0);
// el 객체를 사용한 데이터 표시를 위해서 JSP 기본 저장 객체에 데이터를 담아야만 한다.
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style type="text/css">

.dataRow:hover { /* class 선택. "."을 붙인다. */
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
//alert("자바 스크립트 경고창이 실행됨.")
// body부분의 HTML 객체가 다 로딩된 상태에서 처리되도록 만드는 형식 
// -> function을 저장 -> HTML 로딩 마침 -> 저장된 function을 실행
$(function(){
	// 글보기로 페이지 이동 시키는 처리문 작성
	// 1. 리스트의 한줄을 선택 2. 이벤트 함수를 붙인다. 3. 이벤트 함수에 동작할 처리 함수를 파라메터로 넣어준다.
	// jQuery
	$(".dataRow").click(function(){
		// var no -> js 변수 선언
		// $(this).find(".no").text(); -> jQuery
		var no = $(this).find(".no").text();
		// js
		// alert("글보기로 가야합니다. - " + no);
		// js
		location = "view.jsp?no=" + no + "&inc=1" 
				+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
	});
});
</script>
</head>
<body>
<div class="container">
	<h2><i class="material-icons">&#xe5da;</i> 게시판 리스트</h2>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- request에 들어 있는 list가 비어 있으면 처리 -->
		<c:if test="${empty list }">
		<tr>
			<td colspan="5">데이터가 존재하지 않습니다.	</td>
		</tr>
		</c:if>
		<!-- request에 들어 있는 list에 데이터가 있으면 처리 -->
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo">
				<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
				<tr class="dataRow">
					<td class="no">${vo.no }</td>
					<!-- 자바 -> <a href="view.jsp?no=2">자바</a> -->
					<td>${vo.title }</td>
					<td>${vo.writer }</td>
					<td>${vo.writeDate }</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		</c:if>		
		<tr>
			<td colspan="5">
				<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }"/>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<a href="writeForm.jsp" class="btn btn-default">글쓰기</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
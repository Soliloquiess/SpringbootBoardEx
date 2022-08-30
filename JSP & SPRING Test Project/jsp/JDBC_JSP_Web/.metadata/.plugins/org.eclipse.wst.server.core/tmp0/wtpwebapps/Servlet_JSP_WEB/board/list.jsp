
<%@page import="com.board.service.BoardListService"%>
<%@page import="com.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//자바 부분입니다.
//DBINFO에서 드라이버 확인을 먼저 해야한다.
//Class.forName("com.util.db.DBInfo"); <- Servlet의 init에서 대신 해줌.
request.setAttribute("list", ExecuteService.execute(new BoardListService(), null));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style type="text/css">
.dataRow:hover{
/* 데이터 한 줄에 대해서 마우스가 올라 갔을 떄의 스타일 지정. .dataRow = . class를 의미. class= dataRow라고 작성한 것에 대한 내용 */

background:#eee;
cursor:pointer;
}
</style>
<!-- http://localhost/board/view.jsp?no=2 -->

<h1>게시판 리스트</h1>
<table>
	<!-- 타이틀 출력 -> 데이터가 있는 만큼 한 줄씩 출력한다.(list에 데이터가 없으면 데이터가 없다고 표시) -->
<%-- 	${list } --%>
	<!-- 위에 선언한 리스트 -->

	<!-- tr:table row - 한줄 -형태만 잡아준다. 데이터 표시 불가능
th:table head- 제목에 해당되는 1칸 - 데이터 표시 가능
td:table data- 데이터에 해당되는 한칸  -데이터 표시 가능

-->

	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>

		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:if test="${empty list }">
		<tr>
			<td colspan="5">데이터가 존재하지 않습니다.</td>
		</tr>
	</c:if>

	<c:if test="${!empty list}">
		<!-- 데이터가 있는 만큼 반복 처리한다. -->
		<c:forEach items="${list}" var="vo">
			<tr onclick = "location.href='view.jsp?no=${vo.no}'" class= "dataRow">
			<!-- vo.no => vo.getNo() 으로 코드 변환해서 처리해준다. 프로퍼티-->
				<td>${vo.no }</td>
				<td>${vo.title }</td>
				<td>${vo.writer }</td>
				<td>${vo.writeDate }</td>
				<td>${vo.hit }</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<a href="writeForm.jsp"><button>글쓰기</button></a>
</body>
</html>
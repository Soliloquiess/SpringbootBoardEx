<%@page import="com.board.service.BoardListService"%>
<%@page import="com.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/board_err.jsp"%>
 
<% 
// DB에 관련 확인
Class.forName("com.util.db.DB");
// DB에서 데이터 가져오기
BoardListService service = new BoardListService();
List<BoardVO> list = service.service();
// System.out.println(10/0);
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
</script>
</head>
<body>
<div class="container">
	<h2>게시판 리스트</h2>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<% if(list == null) {%>
		<tr>
			<td colspan="5">데이터가 존재하지 않습니다.	</td>
		</tr>
		<% } else { %>
			<%
			for(BoardVO vo : list){
			%>
				<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
				<tr onclick="document.location='view.jsp?no=<%= vo.getNo() %>&inc=1'" class="dataRow">
					<td><%= vo.getNo() %></td>
					<!-- 자바 -> <a href="view.jsp?no=2">자바</a> -->
					<td><%= vo.getTitle() %></td>
					<td><%= vo.getWriter() %></td>
					<td><%= vo.getWriteDate() %></td>
					<td><%= vo.getHit() %></td>
				</tr>
			<% } // for 문의 끝 %>
		<% } // if 문의 끝 %>
		<tr>
			<td colspan="5">
				<a href="writeForm.jsp" class="btn btn-default">글쓰기</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
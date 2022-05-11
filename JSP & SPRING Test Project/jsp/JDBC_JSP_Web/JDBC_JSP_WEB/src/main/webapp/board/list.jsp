<%@page import="com.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.board.service.BoardListService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
//데이터 가져오기 - 생성하고 호출한다.
//DB 클래스 확인
Class.forName("com.util.db.DB");
//여기가 자바의처리 부분
//[Controller(jsp)]] - service - DAO
BoardListService boardListService = new BoardListService();
List<BoardVO> list = boardListService.service();
System.out.println(list);
%>
<!DOCTYPE html>
<html>
<!--  페이지 정보 -->

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
th, td {
	border: 1px solid;
	padding: 5px;
	align-center;
}
</style>
<script type="java/javascript">

</script>
</head>
<!--  데이터 표시 부분  -->
<body>
	<h2>게시판 리스트</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- 데이터 있는 만큼 반복처리해서 줄을 만들어낸다 -->
		<%
		for (BoardVO vo : list) {
		%>
		<tr>
			<td><%=vo.getNo()%></td>
			<td><a href="view.jsp?no=<%=vo.getNo()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=vo.getWriteDate()%></td>
			<td><%=vo.getHit()%></td>
		</tr>
		<%
		} //for문의 끝
		%>
		<tr>
			<td colspan="5">
				<a href = "writeForm.jsp"><button>글쓰기</button></a>
			</td>
		</tr>
	</table>
</body>
</html>
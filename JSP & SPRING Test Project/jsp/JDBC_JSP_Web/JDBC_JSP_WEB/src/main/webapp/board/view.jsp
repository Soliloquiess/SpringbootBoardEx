<%@page import="com.board.vo.BoardVO"%>
<%@page import="com.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
//Controller(JSP)- BoardViewService-BoardDAO
//BoardViewService 클래스 작성 생성 후 호출
//BoardDAO 클래스에 view (long no) 추가 작성
BoardViewService service = new BoardViewService();
BoardVO vo = service.service(no);
System.out.println("view.jsp - vo : " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type = "text/css">
th,td{
	border : 1px solid;
	padding : 5px;
	align-center;
}

</style>
</head>
<body>
	전달 받은 번호
	<%=no%>
	<h2>게시판 글 보기</h2>
	<table>
		<tr>
			<th>번호</th>
			<td><%=vo.getNo()%></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=vo.getContent()%></td>
		</tr>

		<tr>
			<th>작성자</th>
			<td><%=vo.getWriter()%></td>
		</tr>

		<tr>
			<th>작성일</th>
			<td><%=vo.getWriteDate()%></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=vo.getHit()%></td>
		</tr>

	</table>
	<a href = "updateForm.jsp?no=<%=vo.getNo()%>"><button>수정</button></a>
	<a href = "delete.jsp?no=<%=vo.getNo()%>"><button>삭제</button></a>	
	<a href = "list.jsp"><button>리스트</button></a>
</body>
</html>

<%@page import="com.board.service.BoardViewService"%>
<%@page import="com.main.controller.ExecuteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% 
//자바 부분 - 데이터 처리 할 시 사용
//데이터 수집 - 글 번호가 넘어온다 - 없으면 예외 발생
//application, session, request, pageContext에 담긴 데이터가 아니면EL 객체를 사용할 수 없다.
//넘어오는 데이터 받기
String noStr = request.getParameter("no");


//application, session, request, pageContext에 담긴 데이터는 EL 객체를 사용할 수 있다.
request.setAttribute("vo", ExecuteService.execute(new BoardViewService(), Long.parseLong(noStr)));
//이런식으로 넘겨받은 데이터는 long타입 가져야 됨.	
long no = Long.parseLong(noStr);
%>
<!--  처리 된 데이터 표시할 때 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

</head>
<body>
<h1>게시판 글 보기</h1>
<%-- 글 번호 : <%=no %><br> --%>

<%-- 글 번호 : <%=noStr %><br>
DB에서 가져온 데이터 :${vo}
 --%>
 
<table>

	<tr>
		<th>번호</th>
		<th>${vo.no}</th>
	</tr>
	<tr>
		<th>제목</th>
		<th>${vo.title}</th>
	</tr>
	<tr>
		<th>내용</th>
		<th>${vo.content}</th>
	</tr>
	<tr>
		<th>작성자</th>
		<th>${vo.writer}</th>
	</tr>
	<tr>
		<th>작성일</th>
		<th>${vo.writeDate}</th>
	</tr>
	<tr>
		<th>조회수</th>
		<th>${vo.hit}</th>
	</tr>
</table>
<a href = "updateForm.jsp?no=<%=no%>"><button>수정</button></a>
<a href = "delete.jsp?no=<%=no%>"><button>삭제</button></a>
<a href = "list.jsp?no=<%=no%>"><button>리스트</button></a>
</body>
</html>
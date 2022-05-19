<%@page import="com.board.vo.BoardVO"%>
<%@page import="com.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
// 조회수 1 증가 여부 - 1 : 증가, 0 : 미증가
String strInc = request.getParameter("inc");
long inc = Long.parseLong(strInc);
// Conttoller(JSP) - BoardViewService - BoardDAO
// BoardViewService 클래스 작성 -> 생성 후 호출
// BoardDAO 클래스에 view(long no) 추가 작성
BoardViewService service = new BoardViewService();
BoardVO vo = service.service(no, inc);
// 가져온 데이터를 웹에 표시하면 줄바꿈을 무시된다. \n을 <br> 바꾼다.
vo.setContent(vo.getContent().replace("\n", "<br>"));
// System.out.println("view.jsp - vo : " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>
<style type="text/css">
</style>
</head>
<body>
<div class="container">
<h2>게시판 글보기</h2>
<table class="table">
<tr>
	<th>번호</th>
	<td><%= vo.getNo() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%= vo.getTitle() %></td>
</tr>
<tr>
	<th>내용</th>
	<td><%= vo.getContent() %></td>
</tr>
<tr>
	<th>작성자</th>
	<td><%= vo.getWriter() %></td>
</tr>
<tr>
	<th>작성일</th>
	<td><%= vo.getWriteDate() %></td>
</tr>
<tr>
	<th>조회수</th>
	<td><%= vo.getHit() %></td>
</tr>
</table>

<a href="updateForm.jsp?no=<%= vo.getNo()%>" class="btn btn-default">수정</a>
<a href="delete.jsp?no=<%= vo.getNo()%>" class="btn btn-default">삭제</a>
<a href="list.jsp" class="btn btn-default">리스트</a>
</div>
</body>
</html>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="com.webjjang.board.service.BoardViewService"%>
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
// el 객체를 사용한 데이터 표시를 위해서 JSP 기본 저장 객체에 데이터를 담아야만 한다.
request.setAttribute("vo", vo); // el 객체는 getter를 사용하서 데이터를 꺼낸다.
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
	<td>${vo.no }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${vo.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td>${vo.content }</td>
</tr>
<tr>
	<th>작성자</th>
	<td>${vo.writer }</td>
</tr>
<tr>
	<th>작성일</th>
	<td>${vo.writeDate }</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${vo.hit }</td>
</tr>
</table>

<a href="updateForm.jsp?no=${vo.no }" class="btn btn-default">수정</a>
<a href="delete.jsp?no=${vo.no }" class="btn btn-default">삭제</a>
<a href="list.jsp?page=${param.page }&perPageNum=${param.perPageNum}" class="btn btn-default">리스트</a>
</div>
</body>
</html>
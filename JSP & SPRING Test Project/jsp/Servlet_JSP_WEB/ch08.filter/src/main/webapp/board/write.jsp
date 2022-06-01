<%@page import="com.board.service.BoardWriteService"%>
<%@page import="com.main.controller.ExecuteService"%>
<%@page import="com.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//한글처리
request.setCharacterEncoding("utf-8");
//데이터 수집하기 - writeForm.jsp에서 데이터가 넘어온다.
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");
BoardVO vo = new BoardVO();
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

//DB전달해서 저장한다.
ExecuteService.execute(new BoardWriteService(), vo);

response.sendRedirect("list.jsp");
%>
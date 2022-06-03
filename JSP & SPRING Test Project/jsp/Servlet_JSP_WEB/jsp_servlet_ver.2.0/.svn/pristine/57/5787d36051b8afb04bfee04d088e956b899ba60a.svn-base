<%@page import="com.webjjang.board.service.BoardWriteService"%>
<%@page import="com.webjjang.main.controller.ExecuteService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 처리
request.setCharacterEncoding("utf-8");

// java, JSP
// 데이터 수집하기. - writeForm.jsp에서 데이터가 넘어온다.
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");
// java
BoardVO vo = new BoardVO();
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

//DB 전달해서 저장한다. - java
ExecuteService.execute(new BoardWriteService(), vo);

// JSP - 페이지 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
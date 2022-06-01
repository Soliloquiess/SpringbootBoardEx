<%@page import="com.board.service.BoardUpdateService"%>
<%@page import="com.main.controller.ExecuteService"%>
<%@page import="com.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 처리
request.setCharacterEncoding("utf-8");

// 데이터 수집
String noStr = request.getParameter("no");
Long no = Long.parseLong(noStr);
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");
BoardVO vo = new BoardVO();
vo.setNo(no);
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

// 수집한 데이터를 DB에 보내서 적용시킨다. BoardUpdateService
ExecuteService.execute(new BoardUpdateService(), vo);

// 자동으로 글보기로 가야하고 글번호와 inc 데이터를 같이 보낸다. - 데이터 중 하나라도 넘기지 않으면 오류가 난다.
response.sendRedirect("view.jsp?no=" + no + "&inc=0");
%>
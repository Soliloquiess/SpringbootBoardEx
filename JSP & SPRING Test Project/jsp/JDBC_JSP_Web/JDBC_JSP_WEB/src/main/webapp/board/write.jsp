<%@page import="com.board.service.BoardWriteService"%>
<%@page import="com.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/// 여기는 자바 입니다.
// 넘어오는 데이터 받기
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");

// Controller -> Service -> DAO : BoardVO 객체를 만들어서 전달한다.
BoardVO vo = new BoardVO();
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

System.out.println("게시판 글등록 - " + vo);
// BoardWriteService -> BoardDAO.write
BoardWriteService service = new BoardWriteService();
service.service(vo);

// 게시판 리스트로 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>

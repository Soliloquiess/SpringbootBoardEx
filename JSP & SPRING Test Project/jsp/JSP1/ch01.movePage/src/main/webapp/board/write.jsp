<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 처리
//이부분은 자바입니다
//DB 처리하도록 작성
request.setCharacterEncoding("utf-8");

System.out.println("게시판 처리함.");
//DB 전달해서 저장한다. - java

// JSP - 페이지 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
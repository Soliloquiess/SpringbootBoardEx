
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 처리
request.setCharacterEncoding("utf-8");

//여기가 자바 부분입니다.
System.out.println("update.jsp-데이터 수정 처리");
response.sendRedirect("view.jsp");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

//넘어오는 데이터 받아서 확인하기
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");


System.out.println("제목"+title);
System.out.println("내용"+content);
System.out.println("작성자"+writer);



// 한글 처리
request.setCharacterEncoding("utf-8");

//이클립스 콘솔에 찍힌다.
System.out.println("write.jsp-게시판 글 등록 처리함.");

// JSP - 페이지 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

// 한글 처리
//이부분은 자바입니다
//DB 처리하도록 작성
//넘어오는 데이터 받아서 확인하기
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");

//콘솔 출력 확인
System.out.println("제목"+title);
System.out.println("내용"+content);
System.out.println("글쓴이"+writer);
//DB 전달해서 저장한다. - java

// JSP - 페이지 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집
// 글번호를 DB에 전달해서 지운다.

// 글삭제를 하면 자동으로 리스트 페이지로 이동시킨다.
response.sendRedirect("list.jsp");
%> 
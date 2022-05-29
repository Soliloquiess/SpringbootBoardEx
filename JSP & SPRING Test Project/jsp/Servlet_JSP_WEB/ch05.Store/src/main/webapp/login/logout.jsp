<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% 
session.invalidate();	//세션 종료
response.sendRedirect("main.jsp");
%>
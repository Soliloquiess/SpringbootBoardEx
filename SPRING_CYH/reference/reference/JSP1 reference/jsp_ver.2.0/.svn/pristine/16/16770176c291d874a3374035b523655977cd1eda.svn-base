<%@page import="com.webjjang.notice.service.NoticeDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//데이터 수집 - 글번호
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

System.out.println("delete.jsp - no : " + no);

// DB 삭제
NoticeDeleteService service = new NoticeDeleteService();
int result = service.service(no);

/// 자동으로 list.jsp 로 이동
response.sendRedirect("list.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 삭제</title>
</head>
<body>
공지 삭제
</body>
</html>
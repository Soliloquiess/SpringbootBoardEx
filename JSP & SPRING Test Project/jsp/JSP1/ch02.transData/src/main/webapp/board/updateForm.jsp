<%-- <%@page import="com.webjjang.board.service.BoardViewService"%>
<%@page import="com.webjjang.main.controller.ExecuteService"%>
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--     // 데이터 수집

// 데이터를 DB에 전달하면서 no에 맞는 DB 데이터를 가져 오기
// 여러개의 데이터를 한개로 넘길때 -> 1. 클래스 작성하는 방법. 2. Object 배열을 만든다.
// 데이터 글 수정에서는 조회수 1증가 시키지 않다. 0을 넘기다.
/* request.setAttribute("vo", ExecuteService.execute(new BoardViewService(), new Object[]{no, 0}));*/
//String noStr = request.getParameter("no");
//long no = Long.parseLong(noStr);	
//Long no = Long.parseLong(request.getParameter("no"));
// -->
<%

String noStr = request.getParameter("no");
long no = Long.parseLong(noStr);
String title ="java";
String content = "java jjang";
String writer = "lee";

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		// JS - 이전 페이지
		history.back();
	});
});
</script>

</head>
<body>
<div class="container">
<h1>게시판 글수정 폼</h1>
<!-- html로 데이터 입력 받기 -->
<form action = "update.jsp">
글번호 : <input name = "no" value = "<%= no%>" readonly><br>
제목: <input name = "title" value = "<%=title%>" required="required" pattern = ".{4,20}" 
title = "글 제목을 4자 이상 100자 이내로 입력하세요"><br>
내용: <textarea rows="5" cols="80" name="content" required="required"><%=content%></textarea><br>
작성자: <input name = "writer" required="required"value = "<%=writer %>"><br>
<button>수정</button>
</form>
</div>
</body>
</html>
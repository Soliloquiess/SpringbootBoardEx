<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.member.service.MemberViewService"%>
<%@page import="com.main.controller.ExecuteService"%>

<%
//DB에서 내 정보 가져오기 - 아이디
String id = request.getParameter("id");
request.setAttribute("vo", ExecuteService.execute(new MemberViewService(), id));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 가입한 정보 보기</h1>
아이디: ${vo.id }<br>
이름:${vo.name }<br>
성별:${vo.gender }<br>
생년:${vo.birth }<br>
연락처:${vo.tel }<br>
이메일:${vo.email }<br>
사진:${vo.photo }<br>
<img alt = "내사진" src= "${vo.photo }" width="300px"><br>
가입일:${vo.regDate }<br>
접속일:${vo.conDate }<br>
상태:${vo.status }<br>
등급번호:${vo.gradeNo }<br>
등급명:${vo.gradeName }<br>
</body>
</html>
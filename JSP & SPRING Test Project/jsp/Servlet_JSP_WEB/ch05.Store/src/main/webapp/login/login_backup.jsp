<%-- <%@page import="com.webjjang.member.service.LogInService"%>
<%@page import="com.webjjang.main.controller.ExecuteService"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
LoginVO vo = new LoginVO();
vo.setId(id);
vo.setPw(pw);

LoginVO loginVO = (LoginVO) ExecuteService.execute(new LogInService(), vo);
if(loginVO != null)
	// 로그인 처리 - session에 로그인 정보저장
	session.setAttribute("login", loginVO);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 확인용 페이지</title>
</head>
<body>
<h1>로그인 정보</h1>
${(empty login)?"로그인이 되지 않았습니다. 정보를 다시 확인해 주세요.":""}
아이디 : ${login.id }<br>
이름 : ${login.name }<br>
등급번호 : ${login.gradeNo }<br>
등급명 : ${login.gradeName }<br>
</body>
</html> --%>
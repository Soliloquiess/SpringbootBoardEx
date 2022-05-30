<%@page import="com.member.vo.MemberVO"%>

<%@page import="java.util.Arrays"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 한글 처리

request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");

String pw = request.getParameter("pw");

String name = request.getParameter("name");

String gender = request.getParameter("gender");

String birth = request.getParameter("birth");

String[] tel = request.getParameterValues("tel");

String email = request.getParameter("email");

String photo = request.getParameter("photo");

if (photo == null || photo.equals("")) {
	photo = "/upload/member/noImage.jpg";
}

MemberVO vo = new MemberVO();

vo.setId(id);

vo.setPw(pw);

vo.setName(name);

vo.setGender(gender);

vo.setBirth(birth);

// java - String join(구분문자, 여려개의데이터) : 010-1111-5555

vo.setTel(String.join("-", tel));

vo.setEmail(email);

vo.setPhoto(photo);
%>

​

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>

	<h1>회원가입을 위한 데이터</h1>

	아이디 :
	<%=id%>
	<br> 비밀번호 :
	<%=pw%>
	<br> 이름 :
	<%=name%>
	<br> 성별 :
	<%=gender%>
	<br> 생년월일 :
	<%=birth%>
	<br> 연락처 :
	<%=Arrays.toString(tel)%>
	<br> 이메일 :
	<%=email%>
	<br> 사진 :
	<%=photo%>
	<br>

	<%=vo%>

</body>

</html>
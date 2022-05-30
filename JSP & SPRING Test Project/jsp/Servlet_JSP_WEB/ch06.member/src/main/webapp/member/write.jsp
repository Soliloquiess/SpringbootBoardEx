<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.member.vo.MemberVO"%>

<%@page import="java.util.Arrays"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 한글 처리

request.setCharacterEncoding("utf-8");

//서버의 위치 - webapp(맨앞의 /)아래의 위치
String path = "/upload/member";
//서버 컴퓨터의 실제적으로 저장되는 폴더 위치
String savePath = request.getServletContext().getRealPath(path);
//용량제한
int maxSize=100*1024*1024;
//encoding type
String encoding= "utf-8";

//클라이언트 name = "photo"-> 임시 저장소(메모리 또는 임시 파일)->지정된 위치의 파일명 저장
MultipartRequest multi = new MultipartRequest(request,savePath, maxSize, encoding, new DefaultFileRenamePolicy());
String id = multi.getParameter("id");

String pw = multi.getParameter("pw");

String name = multi.getParameter("name");

String gender = multi.getParameter("gender");

String birth = multi.getParameter("birth");

String[] tel = multi.getParameterValues("tel");

String email = multi.getParameter("email");

String photo = multi.getFilesystemName("photo");
//multi.getFilesystemName :서버에 올라간 실제 파일명
//저장 데이터는 위치(path)+"/"+서버의 파일명
if (photo == null || photo.equals("")) {
	photo = "/upload/member/noImage.jpg";
	
}else{
	photo=path+"/"+photo;
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
	<img src ="<%= photo %>"><br>

	<%=vo%>

</body>

</html>
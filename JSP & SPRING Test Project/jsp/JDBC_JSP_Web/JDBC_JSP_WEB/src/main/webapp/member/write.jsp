<%@page import="com.member.service.MemberWriteService"%>
<%@page import="com.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.member.service.MemberListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
//데이터 가져오기 - 생성하고 호출한다.
//DB 클래스 확인
Class.forName("com.util.db.DB");
request.setCharacterEncoding("utf-8");
//여기가 자바의처리 부분
//[Controller(jsp)]] - service - DAO
String id= request.getParameter("id");
String pw= request.getParameter("pw");
String name= request.getParameter("name");
String gender= request.getParameter("gender");
String birth= request.getParameter("birth");
String tel= request.getParameter("tel");
String email= request.getParameter("email");
String photo= request.getParameter("photo");
//사진이 안 들어오면 NoImage.jpg로 기본 세팅한다.
if(photo==null||photo.equals("")) photo = "/upload/member/noImagejpg";

//[Controller(jsp)]] - service - DAO:MemberVO객체를 만들어서 전달한다
MemberVO vo = new MemberVO();

vo.setId(id);
vo.setPw(pw);
vo.setName(name);
vo.setGender(gender);
vo.setBirth(birth);
vo.setTel(tel);
vo.setEmail(email);
vo.setPhoto(photo);

System.out.println("회원가입 vo-"+vo);
//MemberWriteService->MemberDAO.write

//서비스 생성하고 호출해서 vo넘김.
MemberWriteService service = new MemberWriteService();
service.service(vo);

//회원 관리 리스트로 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
<!DOCTYPE html>
<html>
<!--  페이지 정보 -->

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td {
	border: 1px solid;
	padding: 5px;
	align-center;
}
</style>
<script type="java/javascript">

</script>
</head>
<!--  데이터 표시 부분  -->
<body>
	게시글 등록이 완료되었음.
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
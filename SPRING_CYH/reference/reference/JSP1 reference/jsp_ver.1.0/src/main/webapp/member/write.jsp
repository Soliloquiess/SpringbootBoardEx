<%@page import="com.webjjang.member.service.MemberWriteService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/// 여기는 자바 입니다.
// 한글 처리
request.setCharacterEncoding("utf-8");
// 넘어오는 데이터 받기 - 아이디, 비밀번호, 이름, 성별, 생년월일, 연락처, 이메일, 사진
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String birth = request.getParameter("birth");
String tel = request.getParameter("tel");
String email = request.getParameter("email");
String photo = request.getParameter("photo");
// 사진이 안들어오면 NoImage.jpg로 기본 세팅한다.
if(photo == null || photo.equals("")) photo ="/upload/member/noImage.jpg";

// Controller -> Service -> DAO : MemberVO 객체를 만들어서 전달한다.
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPw(pw);
vo.setName(name);
vo.setGender(gender);
vo.setBirth(birth);
vo.setTel(tel);
vo.setEmail(email);
vo.setPhoto(photo);

System.out.println("회원가입 vo - " + vo);
// MemberWriteService -> MemberDAO.write
MemberWriteService service = new MemberWriteService();
service.service(vo);

// 회원 리스트로 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>

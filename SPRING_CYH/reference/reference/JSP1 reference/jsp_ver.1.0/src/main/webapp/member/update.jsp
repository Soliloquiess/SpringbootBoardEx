<%@page import="com.webjjang.member.service.MemberUpdateService"%>
<%@page import="com.webjjang.member.service.MemberWriteService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/// 여기는 자바 입니다.
// 한글 처리
request.setCharacterEncoding("utf-8");

// 데이터 수집 - 넘어오는 데이터 받기 : 아이디, 비밀번호, 이름, 성별, 생년월일, 전화번호, 이메일
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String birth = request.getParameter("birth");
String tel = request.getParameter("tel");
String email = request.getParameter("email");

// Controller -> Service -> DAO : MemberVO 객체를 만들어서 전달한다.
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPw(pw);
vo.setName(name);
vo.setGender(gender);
vo.setBirth(birth);
vo.setTel(tel);
vo.setEmail(email);

System.out.println("회원정보 수정 처리 - vo : " + vo);

// MemberUpdateService -> MemberDAO.update()
MemberUpdateService service = new MemberUpdateService();
//  result : 1 - 수정 완료, result : 0 - 아이디나 비밀번호가 틀림.
int result = service.service(vo);

// 수정이 성공하면 회원정보보기로 자동 이동시킨다.
if(result == 1) { 
	response.sendRedirect("view.jsp?id=" + id);
	return;
} else{
	// out : JSP에서 객체를 생성해서 넣어주는 변수 - JSP 기본 객체 - 브라우저에 출력하고자 할 때 사용하는 객체
	out.println("아이디나 비밀번호를 확인해 주세요.");
}
%>
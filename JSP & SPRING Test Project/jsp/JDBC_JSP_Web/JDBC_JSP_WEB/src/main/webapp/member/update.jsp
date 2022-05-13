<%@page import="com.member.service.MemberUpdateService"%>
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
//한글처리
request.setCharacterEncoding("utf-8");
//여기가 자바의처리 부분
//[Controller(jsp)]] - service - DAO

//데이터 수집 넘어오는 데이터 받기
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String birth = request.getParameter("birth");
String tel = request.getParameter("tel");
String email = request.getParameter("email");

//[Controller(jsp)]] - service - DAO:MemberVO객체를 만들어서 전달한다
MemberVO vo = new MemberVO();


vo.setId(id);
vo.setPw(pw);
vo.setName(name);
vo.setGender(gender);
vo.setBirth(birth);
vo.setTel(tel);
vo.setEmail(email);
System.out.println("회원 수정-"+vo);
//MemberUpdateService->MemberDAO.update


MemberUpdateService service = new MemberUpdateService();
int result = service.service(vo);
//result 1:수정 완료 0 :틀림


// 수정 성공시 회원 정보보기로 자동 이동시킴.
// response.sendRedirect("list.jsp"); 
if(result == 1){
	response.sendRedirect("view.jsp?id="+id);
	return;
}
else {
	//out:jsp에서 객체를 생성해서 넣어주는 변수 - jsp 기본 객체 - 브라우저에서 출력하고자 할 때 사용.
	out.println("아이디나 비밀번호를 다시 확인해주세요.");
}
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
</html>
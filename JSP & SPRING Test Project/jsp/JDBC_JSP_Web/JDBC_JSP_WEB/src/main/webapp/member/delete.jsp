<%@page import="com.member.vo.MemberVO"%>
<%@page import="com.member.service.MemberDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//한글처리
Class.forName("com.util.db.DB");
request.setCharacterEncoding("utf-8");

//데이터 수집
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String tel = request.getParameter("tel");

//MemberVO 에 담기
MemberVO vo = new MemberVO();

vo.setId(id);
vo.setPw(pw);
vo.setTel(tel);



//생성하고 호출 MemberDeleteService
MemberDeleteService service = new MemberDeleteService();
int result = service.service(vo);

//자동으로 list로 보낸다.
if(result==1) {
	response.sendRedirect("list.jsp");
	return;
}
else out.print("입력 정보 다시 보셈"); 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>
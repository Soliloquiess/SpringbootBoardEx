<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="org.apache.tomcat.util.compat.JrePlatform"%>
<%@page import="com.webjjang.notice.vo.NoticeVO"%>
<%@page import="com.webjjang.notice.service.NoticeViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
// 데이터 수집 - 글번호
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

System.out.println("view.jsp - no : " + no);

// 데이터 불러오기 [Controller - jsp] - service - dao
NoticeViewService service = new NoticeViewService();
NoticeVO vo = service.service(no);
// 내용은 엔터를 적용시키니 위해서 줄바꿈 데이터(\n)를 <br> 태그로 바꿔준다.
vo.setContent(vo.getContent().replace("\n", "<br>"));

System.out.println("view.jsp - vo : " + vo);

// 로그인 정보 꺼내 오기
LoginVO loginVO = (LoginVO) session.getAttribute("login");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 보기</title>
<style type="text/css">
th, td{
	border: 1px solid #444;
	padding: 5px;
}
th{
	text-align: right;
}
</style>

</head>
<body>
<h2>공지 보기</h2>
<table>
<tr>
	<th>번호</th>
	<td><%= vo.getNo() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%= vo.getTitle() %></td>
</tr>
<tr>
	<th>내용</th>
	<td><%= vo.getContent() %></td>
</tr>
<tr>
	<th>공지 시작일</th>
	<td><%= vo.getStartDate() %></td>
</tr>
<tr>
	<th>공지 종료일</th>
	<td><%= vo.getEndDate() %></td>
</tr>
<tr>
	<th>등록일</th>
	<td><%= vo.getUpdateDate() %></td>
</tr>
<tr>
	<td colspan="2">
		<% if(loginVO != null && loginVO.getGradeNo() == 9){ %>
			<a href="updateForm.jsp?no=<%= vo.getNo() %>"><button>수정</button></a>
			<a href="delete.jsp?no=<%= vo.getNo() %>"><button>삭제</button></a>
		<% } %>
		<a href="list.jsp"><button>리스트</button></a>
	</td>
</tr>
</table>
</body>
</html>
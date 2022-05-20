<%@page import="com.member.vo.LoginVO"%>
<%@page import="org.apache.tomcat.util.compat.JrePlatform"%>
<%@page import="com.notice.vo.NoticeVO"%>
<%@page import="com.notice.service.NoticeViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
// LoginVO loginVO = (LoginVO) session.getAttribute("login");

request.setAttribute("vo", vo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 보기</title>
<style type="text/css">
</style>

</head>
<body>
<div class="container">
<h2>공지 보기</h2>
<table class="table">
<tr>
	<th>번호</th>
	<td>${vo.no }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${vo.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td>${vo.content }</td>
</tr>
<tr>
	<th>공지 시작일</th>
	<td>${vo.startDate }</td>
</tr>
<tr>
	<th>공지 종료일</th>
	<td>${vo.endDate }</td>
</tr>
<tr>
	<th>등록일</th>
	<td>${vo.updateDate }</td>
</tr>
<tr>
	<td colspan="2">
		<c:if test="${!empty login && login.gradeNo ==9}">
			<a href="updateForm.jsp?no=${vo.no }"><button>수정</button></a>
			<a href="delete.jsp?no=${vo.no }"><button>삭제</button></a>
		</c:if>
		<a href="list.jsp"><button>리스트</button></a>
	</td>
</tr>
</table>
</div>
</body>
</html>
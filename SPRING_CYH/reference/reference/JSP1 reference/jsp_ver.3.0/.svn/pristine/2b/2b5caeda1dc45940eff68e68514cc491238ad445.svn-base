<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.notice.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.notice.service.NoticeListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%
// 데이터 수집
// 페이지 정보 전달받기
PageObject pageObject = PageObject.getInstance(request);

// now - 현재 공지, old - 지난 공지, res - 예약 공지, all - 전체 공지 -> pt(point)
// pt = pageObject.period
String pt = request.getParameter("pt");
if (pt == null) pt = "now"; // 기본 값 세팅 : 현재 공지

pageObject.setPeriod(pt);

// DB에서 데이터 가져오기 - 생성, 호출
NoticeListService service = new NoticeListService();
List<NoticeVO> list = service.service(pageObject);

// 데이터 확인하기
System.out.println(list);

// 클릭한 버튼의 스타일 문자열 변수
String style = "background: #660033;color : white;";

// session에서 로그인 정보를 받아낸다.
// LoginVO loginVO = (LoginVO) session.getAttribute("login");

request.setAttribute("list", list);
request.setAttribute("pt", pt);
request.setAttribute("style", style);
request.setAttribute("pageObject", pageObject);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>

<style type="text/css">
.dataRow:hover {
	cursor: pointer; /* 손가락 */
	background: #eee;
}
</style>

<script type="text/javascript">
// alert("${pt}"); // 경고창 띄우기
</script>
</head>
<body>
<div class="container">
<h2>공지사항 리스트</h2>
<a href="list.jsp?pt=now" style='${(pt=="now")?style:""}' class="btn btn-default">현재</a>
<a href="list.jsp?pt=old" style='${(pt=="old")?style:""}' class="btn btn-default">지난</a>
<a href="list.jsp?pt=res" style='${(pt=="res")?style:""}' class="btn btn-default">예약</a>
<a href="list.jsp?pt=all" style='${(pt=="all")?style:""}' class="btn btn-default">전체</a>
<table class="table">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>시작일</th>
	<th>종료일</th>
	<th>등록</th>
</tr>
<c:if test="${empty list }">
	<tr>
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
</c:if>
<c:if test="${!empty list }">
	<c:forEach items="${list }" var="vo">
		<tr onclick="location='view.jsp?no=${vo.no}'" class="dataRow">
			<td>${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.startDate }</td>
			<td>${vo.endDate }</td>
			<td>${vo.updateDate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5" style="text-align: center;">
			<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }" query="&pt=${pt }"/>
		</td>
	</tr>
</c:if>

<c:if test="${!empty login && login.gradeNo == 9 }">
	<tr>
		<td colspan="5" style="text-align: center;">
			<a href="writeForm.jsp" class="btn btn-default">등록</a>
		</td>
	</tr>
</c:if>
</table>
</div>
</body>
</html>
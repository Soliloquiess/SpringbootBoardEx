<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.member.service.MemberListService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// DB에 관련 확인
Class.forName("com.webjjang.util.db.DB");

// 페이지 정보 받기
PageObject pageObject = PageObject.getInstance(request);

// DB에서 데이터 가져오기
MemberListService service = new MemberListService();
List<MemberVO> list = service.service(pageObject);

// el 객체나 JSTL을 사용하기 위해서 request 객체에 담기
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style type="text/css">
.dataRow:hover { /* class 선택. "."을 붙인다. */
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
//alert("자바 스크립트 경고창이 실행됨.")
</script>
</head>
<body>
<div class="container">
	<h2>회원 리스트</h2>
	<table class="table">
		<tr>
			<th>아이디</th>
			<th>사진</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급번호</th>
			<th>등급명</th>
			<th>최근접속날자</th>
		</tr>
		<c:forEach items="${list }" var="vo">
			<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
			<tr onclick="document.location='view.jsp?id=${vo.id}&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }'" class="dataRow">
				<!-- 자바 -> <a href="view.jsp?id=test">id</a> -->
				<td>${vo.id }</td>
				<td><img src="${vo.photo }" style="width: 40px; height: 50px;" class="img-rounded"></td>
				<td>${vo.name }</td>
				<td>${vo.birth }</td>
				<td>${vo.tel }</td>
				<td>${vo.gradeNo }</td>
				<td>${vo.gradeName }</td>
				<td>${vo.conDate }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="8">
				<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }" />
			</td>
		</tr>
		<tr>
			<td colspan="8">
				<a href="writeForm.jsp" class="btn btn-default">회원등록</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
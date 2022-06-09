<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 리스트</title>
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
.noRead{
	color: #900;
	font-weight: 700;
}
</style>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no;
	});
});
</script>

</head>
<body>
<div class="container">
<h1>메시지 리스트</h1>
<ul class="nav nav-tabs">
  <li ${(empty pageObject.acceptMode || pageObject.acceptMode == 3)?"class='active'":""}>
  	<a href="list.do?mode=3">모두</a></li>
  <li ${(pageObject.acceptMode == 1)?"class='active'":""}>
  	<a href="list.do?mode=1">받은 메시지</a>
  </li>
  <li ${(pageObject.acceptMode == 2)?"class='active'":""}><a href="list.do?mode=2">보낸 메시지</a></li>
  <li ${(pageObject.acceptMode == 4)?"class='active'":""}><a href="list.do?mode=4">새로운 메시지</a></li>
</ul>
<div style="padding: 20px 10px 10px 10px;">
	<table class="table">
	<tr>
		<th>번호</th>
		<th>보낸사람(아이디)</th>
		<th>보낸날짜</th>
		<th>받는사람(아이디)</th>
		<th>받은날짜</th>
	<tr>
	<c:if test="${empty list }">
		<tr>
			<td colspan="5">메시지가 존재하지 않습니다.</td>
		</tr>
	</c:if>
	<c:if test="${!empty list }">
		<c:forEach items="${list }" var="vo">
			<tr class='dataRow ${(empty vo.acceptDate)?"noRead":"" }'>
				<td class="no">${vo.no }</td>
				<td>${vo.senderName }(${vo.sender })</td>
				<td>${vo.sendDate }</td>
				<td>${vo.accepterName }(${vo.accepter })</td>
				<td>${(empty vo.acceptDate)?"읽지 않음":vo.acceptDate }</td>
			</tr>
		</c:forEach>
	</c:if>
	
	</table>
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" />
	<div>
		<a href="writeForm.do" class="btn btn-default">보내기</a>
	</div>
</div>
</div>
</body>
</html>
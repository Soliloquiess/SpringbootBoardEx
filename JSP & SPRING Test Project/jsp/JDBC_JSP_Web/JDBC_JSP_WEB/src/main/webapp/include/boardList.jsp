<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3>게시판</h3>
	</div>
	<div class="panel-body">
		<table class="table">
		<c:forEach items="${boardList }" var="vo">
			<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
			<tr class="boardDataRow" data-no="${vo.no }">
				<td class="no">${vo.no }</td>
				<!-- 자바 -> <a href="view.jsp?no=2">자바</a> -->
				<td>${vo.title }</td>
				<td>${vo.writer }</td>
				<td>${vo.writeDate }</td>
				<td>${vo.hit }</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>

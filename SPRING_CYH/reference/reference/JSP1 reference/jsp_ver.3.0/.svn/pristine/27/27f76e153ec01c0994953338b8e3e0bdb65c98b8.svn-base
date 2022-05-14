<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.qna.vo.QnaVO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.qna.service.QnaListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%
// 자바 부분입니다.

// 넘어오는 페이지 정보를 받아서 페이지 객체 생성
PageObject pageObject = PageObject.getInstance(request);

// 리스트 데이터를 가져오기 위한 객체 생성
QnaListService service = new QnaListService();
List<QnaVO> list = service.service(pageObject);

// EL 객체나 JSTL 객로 사용하기 위해서 기본 저장 객체(request)에 담는다.
// EL 라이브러리는 톰캣에 포함. JSTL을 별도로 다운 받아서 /WEB-INF/lib에 넣어 두셔야 합니다.
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);
%>
<!DOCTYPE html>
<html>
<!-- head : html의 설정 -> sitemesh에서는 적용이 안되는 부분의 주석 -->
<head>
<meta charset="UTF-8">
<title>질문답변</title>
<style type="text/css">
.dataRow:hover{
	background: #eee;
	cursor: pointer;
}
</style>
<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		// alert("데이터 클릭~");
		// .data("no") -> body 태그 안에 data-no 속성으로 데이터가 저장되어 있는 것을 가져온다.
		var no = $(this).data("no");
		// alert(no);
		location = "view.jsp?no=" + no + "&inc=1";
	});
});
</script>
</head>
<!-- body : 데이터가 표시되는 부분 -> sitemesh에서는 적용이 안되는 부분의 주석  -->
<body>
<div class="container">
<h2>질문답변</h2>
<!-- 테이블에 데이터 표시 -->
<table class="table">
	<!-- 제목 줄 표시 -->
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<!-- 데이터가 있는 만큼 JSTL의 반복문을 이용해서 tr을 만들어서 표시. 한개의 데이터를 vo에 담는다. -->
	<c:forEach items="${list }" var="vo">
	<!-- 데이터 한줄 정의 : class="dataRow", 글번호를 tr 태그 안에 data-no로 숨겨둔다. -->
	<tr class="dataRow" data-no="${vo.no }">
		<td>${vo.no }</td>
		<td>
			<!-- 들여쓰기 levNo를 이용해서 공백문자 표시 3칸 적용. &nbsp; - 공백문자 
				 begin : 시작 숫자, end : 끝 숫자 -->
			<c:forEach begin="1" end="${vo.levNo * 2 }">
				&nbsp;
			</c:forEach>
			<!-- 답변글 기호 표시 -->
			<c:if test="${vo.levNo > 0 }">
				<i class="material-icons">&#xe5da;</i>
			</c:if>
			${vo.title }
		</td>
		<td>${vo.name }(${vo.id })</td>
		<td>${vo.writeDate }</td>
		<td>${vo.hit }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }"/>
		</td>
	</tr>
	<!-- 로그인을 한 경우만 질문하기 버튼이 나타난다. -->
	<c:if test="${!empty login }">
	<tr>
		<td colspan="5">
			<a href="writeForm.jsp" class="btn btn-default">질문하기</a>
		</td>
	</tr>
	</c:if>
</table>
</div>
</body>
</html>
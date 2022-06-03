<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
// java / jsp
// 데이터를 가져오는 것은 BoardController에서 해결했다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 - default_decorator.jsp에서 작성 -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!-- sitemesh쓰려고 위 부트스트랩은 전부 주석처리 함 -->

<!-- css - 꾸미기 -->
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
// jquery ( == $)  - HTML의 로딩이 끝나면 동작
$(function(){
	// 데이터 한 줄을 클릭하면 글번호, inc와 함께 글보기로 이동한다.
	$(".dataRow").click(function(){
		//alert("글보기 이동 클릭");
		var no = $(this).find(".no").text();
		// js : 페이지 이동 location.href = location
		location = "view.do?no=" + no + "&inc=1";
	});
});
</script>

</head>
<body>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<div class="container">
<h1>게시판 리스트</h1>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<table class="table">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
<!-- JSTL if문 test=조건. empty list -> JSP EL : empty - list가 null이거나 list.size() == 0 -->
<c:if test="${empty list }">
	<tr>
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
</c:if>
<c:if test="${!empty list }">
	<!-- c:forEach : JSTL - 반복문 items = "여러개의 데이터" var = "변수"
		 begin = "시작번호" end = "끝번호" step = "증감값"
		 varStatus = "반복회수나 인덱스 해당되는 정보가 있는 변수"
		 list의 데이터는 서버 저장 객체중 어디에 있을까요? request -->
	<c:forEach items="${requestScope.list }" var="vo">
		<!-- tr 객체를 쉽게 선택할 수 있도록하기 위한 class 지정 -->
		<tr class="dataRow">
			<!-- vo의 데이터는 서버 저장 객체중 어디에 있을까요? pageContext
				EL 객체는 pageContext => request => session => application에 key가지고 있는 데이터 표시 -->
			<td class="no" >${pageScope.vo.no }</td>
			<td >${vo.title }</td>
			<td >${vo.writer }</td>
			<td >${vo.writeDate }</td>
			<td >${vo.hit }</td>
		</tr>
	</c:forEach>
</c:if>
</table>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<a href="writeForm.do" class="btn btn-default">글쓰기</a>
</div>
</body>
</html>
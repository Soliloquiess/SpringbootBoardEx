<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// java / jsp
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
		location = "view.jsp?no=" + no + "&inc=1";
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

</table>

<!-- <a href="view.jsp" class="btn btn-default">자바입니다.</a><p> -->
<span onclick = "location.href='view.jsp?no=2'" class="btn btn-default">자바입니다.</a><p>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<a href="writeForm.jsp" class="btn btn-default">글쓰기</a>

</div>
</body>
</html>
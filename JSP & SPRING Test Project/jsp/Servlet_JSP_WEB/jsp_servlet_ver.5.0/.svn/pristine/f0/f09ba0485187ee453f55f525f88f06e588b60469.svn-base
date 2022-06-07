<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
// jquery() - html 문서가 로딩이 되면 함수를 호출해서 처리해 주세요~.
$(function(){
	// 이벤트 처리
	// 대상 선택.이벤트(function(){})
	// 대상 선택 - $("tag"), $(".class"), $("#id")
	$("#updateBtn").click(function(){
		// alert("글 수업 버튼 클릭");
		location = "updateForm.do?no=${vo.no}";
	});
	$("#deleteBtn").click(function(){
		// alert("글 수업 버튼 클릭");
		// 한번은 물어보자. - 확인을 클릭하면 삭제하러 간다. 그렇지 않으면 아무일도 하지 않는다..
		if(confirm("정말 삭제하시겠습니까?"))
			location = "delete.do?no=${vo.no}";
	});
	$("#listBtn").click(function(){
		// alert("글 수업 버튼 클릭");
		location = "list.do";
	});
});
</script>

</head>
<body>
<div class="container">
<h1>게시판 글보기</h1>
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
	<!-- bootstrap으로 디자인을 하게 되면 설정이 안되는 경우가 있다. 디자인의 우선 순위가 있어서 그렇다.
	     개별 태그에 style 속성을 설정하면 가장 먼저 적용이 된다. -->
	<td><pre style="background: none;border: none;">${vo.content }</pre></td>
</tr>
<tr>
	<th>작성자</th>
	<td>${vo.writer }</td>
</tr>
<tr>
	<th>작성일</th>
	<td>${vo.writeDate }</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${vo.hit }</td>
</tr>
</table>
<!-- button tag는 form tag 안에 없으면 아무 동작도 하지 않는다. -->
<button id="updateBtn" class="btn btn-default">수정</button>
<button id="deleteBtn" class="btn btn-default">삭제</button>
<button id="listBtn" class="btn btn-default">리스트</button>
</div>
</body>
</html>
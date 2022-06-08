<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#cancelBtn").click(function(){
		// JS - 이전 페이지
		history.back();
	});
});
</script>

</head>
<body>
<div class="container">
<h1>게시판 글수정 폼</h1>
<!-- html로 데이터 입력 받기 -->
<form action="update.do" method="post">
<input name="page" type="hidden" value="${param.page }">
<input name="perPageNum" type="hidden" value="${param.perPageNum }">
<input name="key" type="hidden" value="${param.key }">
<input name="word" type="hidden" value="${param.word }">
<table class= "table">
<tr>
	<th>글번호</th>
	<td><input name="no" class="form-control" value="${vo.no }" readonly="readonly"></td>
</tr>
<tr>
	<th>제목</th>
	<td><input name="title" class="form-control" value="${vo.title }"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="7" name="content" class="form-control">${vo.content }</textarea></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input name="writer" class="form-control" value="${vo.writer }"></td>
</tr>
</table>
<!-- button의 기본 tpye - submit ==> button을 클리하면 submit()를 호출해서 데이터와 함께 연결된 페이지로 이동 -->
<button>수정</button>
<button type="reset">새로입력</button>
<!-- type="button" - 버튼으로 보이지만 동작을 하지 않는다. 동작을 JS로 작성한다. -->
<button type="button" id="cancelBtn">취소</button>
</form>

</div>
</body>
</html>
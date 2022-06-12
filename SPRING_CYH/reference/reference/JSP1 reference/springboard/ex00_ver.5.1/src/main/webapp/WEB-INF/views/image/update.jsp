<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 정보 수정 폼</title>
</head>
<body>
<div class="container">
	<h2>이미지 정보 수정 폼</h2>
	<form action="update.do" method="post" >
		<input name="page" value="${param.page }" type="hidden">
		<input name="perPageNum" value="${param.perPageNum }" type="hidden">
		<input name="key" value="${param.key }" type="hidden">
		<input name="word" value="${param.word }" type="hidden">
		<div class="form-group">
			<label>번호</label>
			<input name="no" class="form-control" value="${vo.no }" readonly="readonly">
		</div>
		<div class="form-group">
			<label>제목</label>
			<input name="title" class="form-control" value="${vo.title }">
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea rows="7" name="content" class="form-control">${vo.content }</textarea>
		</div>
		<button class="btn btn-default">수정</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
	</form>
</div>
</body>
</html>
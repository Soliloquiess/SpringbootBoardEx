<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>
</head>
<body>
<div class="container">
<h2>게시판 글쓰기 폼</h2>
<!-- 사용자에게 데이터를 입력하도록 한다. : form, input, select, textarea tag -->
<!-- action : 데이터를 받을 URL, method : get - URL 뒤에 데이터, post - 따로 보이지 않게 데이터
	 Bootstrap - class="form-horizontal" : 한줄에 라벨과 입력을 같이 둔다.
	 col-sm-2 : col - 칸, sm - 해상도, 2 - 너비(총12까지 사용) -->
<form action="write.jsp" method="post" class="form-horizontal" >
		<div class="form-group">
			<label for="title" class="control-label col-sm-2" >제목</label>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<div class="col-sm-10">
				<input type="text" name="title" maxlength="100" id="title" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="control-label col-sm-2" >내용</label>
			<div class="col-sm-10">
				<textarea rows="7" cols="80" name="content" id="content" class="form-control"></textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="writer" class="control-label col-sm-2">작성자</label>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<div class="col-sm-10">
				<input type="text" name="writer" maxlength="10" id="writer" class="form-control"/>
			</div>
		</div>
		<div class="text-center">
			<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">다시 입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
</form>
</div>
</body>
</html>
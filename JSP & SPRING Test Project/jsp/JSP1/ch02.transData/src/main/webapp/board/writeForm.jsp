<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>


<script type="text/javascript">

</script>

</head>
<body>
<div class="container">
<h1>게시판 글쓰기 폼</h1>
<!-- html로 데이터 입력 받기 -->
<form action="write.jsp" method="post">
<!--  write.jsp?title=java  이렇게 쓴거와 동일 아래 타이틀이 여기 queryString에 들어간거-->
<table class= "table">
<tr>
	<th>제목</th>
	<td><input name="title" class="form-control"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="7" name="content" class="form-control"></textarea></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input name="writer" class="form-control"></td>
</tr>
</table>
<!-- button의 기본 tpye - submit ==> button을 클리하면 submit()를 호출해서 데이터와 함께 연결된 페이지로 이동 -->
<button>등록</button>
<button type="reset">새로입력</button>
<!-- type="button" - 버튼으로 보이지만 동작을 하지 않는다. 동작을 JS로 작성한다. -->
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>
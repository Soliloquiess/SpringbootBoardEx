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


<!-- write.jsp?title = java -->
제목: <input name = "title" placeholder = "제목을 입력하세요"><br>
내용: <textarea rows="5" cols="80" name="content"></textarea><br>
작성자: <input name = "writer"><br>
<button>등록</button>
</form>
</div>
</body>
</html>
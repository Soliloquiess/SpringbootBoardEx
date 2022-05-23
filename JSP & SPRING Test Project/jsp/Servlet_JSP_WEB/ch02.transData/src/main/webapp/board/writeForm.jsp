<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>

</head>
<body>
		<h1>게시판 글쓰기 폼</h1>


		<!-- html로 데이터 입력 받기 -->
		<form action="write.jsp" method="post">
			<!--  form은 서버 데이터 넘길떄 쓰고 클릭하면 데이터가 전달 되면서 페이지 이동하는 html from tag

	입력 데이터 태그 : <input> , <textarea>, <select>
	입력 방식 : get - 데이터가 주소 뒤에 붙는다. post = 보이지 않는다. -->
			<!-- write.jsp?title=java -->

			제목: <input name="title" placeholder="제목 입력"><br> 
			내용: <textarea rows="5" cols="80" name="content"></textarea> <br> 
			작성자 : <input name="writer"><br>
			<button>등록</button>

		</form>
</body>
</html>
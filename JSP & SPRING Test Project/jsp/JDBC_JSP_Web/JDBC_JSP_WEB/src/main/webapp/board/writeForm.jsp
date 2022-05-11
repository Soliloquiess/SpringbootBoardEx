<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 글쓰기 폼</title>
</head>
<body>
	<h2>글 쓰기 폼</h2>
	<!-- 사용자에게 데이터를 입력하도록 한다. -->
	<!-- action: 데이터를 받을 URL, method : get-URL 뒤에 데이터, post: 뒤에 따로 보이지 않게 데이터 전송 -->
	<form action="write.jsp" method = "post">
		<table>
			<tr>
				<th>제목</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="title" maxlength="100" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="7" cols="80" name="content"></textarea></td>
			</tr>

			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" maxlength="10">
				</textarea></td>
			</tr>


			<tr>
				<td colspan="2">
				<!--  버튼이 form 태그 안에 있으면 데이터를 전달하는 동작을 하게  된다.	 -->
					<button type="submit">등록</button>
					<button type="reset">다시 입력</button>
					<button type ="button" onclick = "history.back()">취소</button>
					
				</td>
			</tr>


		</table>
	</form>
</body>
</html>
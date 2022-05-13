<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>
</head>
<body>
	<h2>글 쓰기 폼</h2>
	<!-- 사용자에게 데이터를 입력하도록 한다. -->
	<!-- action: 데이터를 받을 URL, method : get-URL 뒤에 데이터, post: 뒤에 따로 보이지 않게 데이터 전송 -->
	<form action="write.jsp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="id" maxlength="100" /></td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="password" name="pw" maxlength="20" /></td>
			</tr>

			<tr>
				<th>비밀번호 확인</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="password" name="pw2" maxlength="20" /></td>
			</tr>

			<tr>
				<th>이름</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="name" maxlength="20" /></td>
			</tr>

			<tr>
				<th>성별</th>
				<!--  input 데이터 입력, type = 입력형태  type=radio = name이 같은것 끼리 세트로 묶음 한개가 	선택되면 나머지는 해제 넘어가는 값은 value 속성, 맨처음 선택되어져야 하는 곳에 checked 속성 넣는다.-->

				<td><label> <input type="radio" name="gender"
						value="남자" checked="checked" />남자
				</label> <label> <input type="radio" name="gender" value="여자" />여자
				</label></td>
			</tr>


			<tr>
				<th>생년월일</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="birth" maxlength="10"
					placeholder="예)2000-00-00" /></td>
			</tr>


			<tr>
				<th>연락처</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="tel" maxlength="20"
					placeholder="예)010-1234-2494" /></td>
			</tr>


			<tr>
				<th>이메일</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="email" maxlength="50"
					placeholder="예)test@naver.com" /></td>
			</tr>



			<tr>
				<th>사진</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="file" name="photo" maxlength="10" /></td>
			</tr>

			<tr>
				<td colspan="2">
					<!--  버튼이 form 태그 안에 있으면 데이터를 전달하는 동작을 하게  된다.	 -->
					<button type="submit">등록</button>
					<button type="reset">다시 입력</button>
					<button type="button" onclick="history.back()">취소</button>

				</td>
			</tr>


		</table>
	</form>
</body>
</html>
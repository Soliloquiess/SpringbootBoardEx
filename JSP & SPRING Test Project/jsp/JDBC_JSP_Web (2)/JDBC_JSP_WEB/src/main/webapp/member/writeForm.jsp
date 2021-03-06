<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<h2>회원가입 폼</h2>
<!-- 사용자에게 데이터를 입력하도록 한다. : form, input, select, textarea tag -->
<!-- 입력 데이터 : 아이디, 비밀번호, 비밀번호확인, 이름, 성별, 생년월일, 연락처, 이메일, 사진 -->
<!-- action : 데이터를 받을 URL, method : get - URL 뒤에 데이터, post - 따로 보이지 않게 데이터 -->
<form action="write.jsp" method="post" enctype="multipart/form-data">	
<!--  여기서 넘겨줄 때 multipart/form-data가 아니면 파일을 넘길 수가 없다. -->
	<table>
		<tr>
			<th>아이디</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="id" maxlength="20" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력
				type="password" : 입력한 문자를 대체 문자로 보여준다. 동구라미-->
			<td><input type="password" name="pw" maxlength="20" /></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="password" name="pw2" maxlength="20" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="name" maxlength="10" /></td>
		</tr>
		<tr>
			<th>성별</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 
				type="radio" : name이 같은 것은 것끼리 세트로 움직임 한개가 선택되면 나머지는 해제된다. 
				넘어 가는 값은 value 속성, 맨처음 선택되어져야 하는 곳에 checked 속성을 넣는다. -->
			<td>
				<label>
				<input type="radio" name="gender" value="남자" checked="checked"/> 남자
				</label>
				<label>
				<input type="radio" name="gender" value="여자"/> 여자
				</label>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 
				xxxx-xx-xx placeholder :입력 형식 예를 데이터가 입력이 안 되었을 때 백그라운드로 표시 -->
			<td><input type="text" name="birth" maxlength="10" placeholder="예)1900-01-01" /></td>
		</tr>
		<tr>
			<th>연락처</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="tel" maxlength="13" placeholder="예)010-1111-2222" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="email" maxlength="50" placeholder="예)test@naver.com" /></td>
		</tr>
		<tr>
			<th>사진</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="file" name="photo" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
				<button type="submit">가입</button>
				<button type="reset">다시 입력</button>
				<button type="button" onclick="history.back()">취소</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
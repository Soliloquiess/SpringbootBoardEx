<%@page import="com.member.vo.MemberVO"%>
<%@page import="com.member.service.MemberViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//자바 부분입니다.
//데이터 받기 - 아이디-> 내 정보 보기 :view.jsp
String id = request.getParameter("id");
System.out.println("updateForm.jsp - id:" + id);
//DB 글 번호에 맞는 데이터 가져온다. - MemberViewService 생성 - 호출
MemberViewService service = new MemberViewService();
MemberVO vo = service.service(id); //아이디 받아옴
System.out.println("updateForm.jsp - vo " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 폼</title>

<!-- 회원등록 화면과 화면 비슷함.
수정항목 : 이름, 성별, 생년, 전번, 이멜, 본인확인 입력정보: 아디(변경 불가), 비번(입력) -->

</head>
<body>
	<h2>회원 정보 수정</h2>


	<form action="update.jsp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="id" maxlength="100"
					value="<%=vo.getId()%>" readonly="readonly"
					onclick="alert('아이디는 수정할 수 없습니다.')" /></td>
			</tr>

			<tr>
				<th>비밀번호(본인확인용)</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="password" name="pw" maxlength="20" /></td>
			</tr>



			<tr>
				<th>이름</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="name" maxlength="20"
					value="<%=vo.getName()%>" /></td>
			</tr>

			<tr>
				<th>성별</th>
				<!--  input 데이터 입력, type = 입력형태  type=radio = name이 같은것 끼리 세트로 묶음 한개가 	선택되면 나머지는 해제 넘어가는 값은 value 속성, 맨처음 선택되어져야 하는 곳에 checked 속성 넣는다.-->

				<td><label> <input type="radio" name="gender"
						value="남자" <%= (vo.getGender().equals("남자"))?"checked":""%> />남자
				</label> 
				<label> <input type="radio" name="gender" value="여자"
						<%= (vo.getGender().equals("여자"))?"checked":""%> />여자
				</label></td>
			</tr>


			<tr>
				<th>생년월일</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="birth" maxlength="10"
					placeholder="예)2000-00-00" value="<%=vo.getBirth()%>" /></td>
			</tr>


			<tr>
				<th>연락처</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="tel" maxlength="20"
					placeholder="예)010-1234-2494" value="<%=vo.getTel()%>" /></td>
			</tr>


			<tr>
				<th>이메일</th>
				<!--  input 데이터 입력, type = 입력형태 -->

				<td><input type="text" name="email" maxlength="50"
					placeholder="예)test@naver.com" value="<%=vo.getEmail()%>" /></td>
			</tr>



			<tr>
				<td colspan="2">
					<!--  버튼이 form 태그 안에 있으면 데이터를 전달하는 동작을 하게  된다.	 -->
					<button type="submit">수정</button>
					<button type="reset">다시 입력</button>
					<button type="button" onclick="history.back()">취소</button>
				</td>
			</tr>


		</table>
	</form>

</body>
</html>
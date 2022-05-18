<%@page import="com.member.service.MemberListService"%>
<%@page import="com.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// DB에 관련 확인
Class.forName("com.util.db.DB");
// DB에서 데이터 가져오기
MemberListService service = new MemberListService();
List<MemberVO> list = service.service();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style type="text/css">
th, td{/* 태그 선택 */
	border: 1px solid #888;
	padding: 5px;
}

.dataRow:hover { /* class 선택. "."을 붙인다. */
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
//alert("자바 스크립트 경고창이 실행됨.")
</script>
</head>
<body>
	<h2>회원 리스트</h2>
	<table>
		<tr>
			<th>아이디</th>
			<th>사진</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급번호</th>
			<th>등급명</th>
			<th>최근접속날자</th>
		</tr>
		<%
		for(MemberVO vo : list){
		%>
			<!-- no : 보여줄 글번호, inc - 조회수 증가 여부. 1:증가, 0:미증가 -->
			<tr onclick="document.location='view.jsp?id=<%= vo.getId() %>'" class="dataRow">
				<!-- 자바 -> <a href="view.jsp?id=test">id</a> -->
				<td><%= vo.getId() %></td>
			
				<td><img src="<%= vo.getPhoto() %>" style="width: 40px; height: 50px;"></td>
			
				<td><%= vo.getName() %></td>
				<td><%= vo.getBirth() %></td>
				<td><%= vo.getTel() %></td>
				<td><%= vo.getGradeNo() %></td>
				<td><%= vo.getGradeName() %></td>
				<td><%= vo.getConDate() %></td>
			</tr>
		<% } // for 문의 끝 %>
		<tr>
			<td colspan="8">
				<a href="writeForm.jsp"><button>회원등록</button></a>
			</td>
		</tr>
	</table>
</body>
</html>
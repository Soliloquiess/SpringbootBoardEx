<%@page import="com.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.member.service.MemberListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
//데이터 가져오기 - 생성하고 호출한다.
//DB 클래스 확인
Class.forName("com.util.db.DB");
//여기가 자바의처리 부분
//[Controller(jsp)]] - service - DAO
MemberListService service = new MemberListService();
List<MemberVO> list = service.service();
System.out.println(list);
%>
<!DOCTYPE html>
<html>
<!--  페이지 정보 -->

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td {	/*태그 선택 */
	border: 1px solid;
	padding: 5px;
}

.dataRow:hover {/*클래스 선택, "."를 붙인다.*/
/*마우스 올라갔을때의 동작*/
	cursor: pointer; 
	background: #eee;
}
</style>
<script type="java/javascript">

</script>
</head>
<!--  데이터 표시 부분  -->
<body>
	<h2>회원 리스트</h2>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급번호</th>
			<th>등급명</th>
			<th>최근 접속 날짜</th>
		</tr>
		<!-- 데이터 있는 만큼 반복처리해서 줄을 만들어낸다 -->
		<%
		for (MemberVO vo : list) {
		%>
		<!-- no:보여줄 글 번호, inc:조회수 증가 여부 1:증가, 0 :미증가 -->
		<tr onclick="document.location='view.jsp?id=<%=vo.getId()%>'" class="dataRow">
			<%-- <td><a href="view.jsp?no=<%=vo.getNo()%>"><%=vo.getTitle()%></a></td> --%>
			
			<td><%=vo.getId()%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getBirth()%></td>
			<td><%=vo.getTel()%></td>
			<td><%=vo.getGradeNo()%></td>
			<td><%=vo.getGradeName()%></td>
			<td><%=vo.getConDate()%></td>
			
		</tr>
		<%
		} //for문의 끝
		%>
		<tr>
			<td colspan="7"><a href="writeForm.jsp"><button>글쓰기</button></a>
			</td>
		</tr>
	</table>
</body>
</html>
<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.image.service.ImageListService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집 - 없다.
// DB에서 데이터를 가져온다. - controller(JSP) - ImageListService - ImageDAO
ImageListService service = new ImageListService();
List<ImageVO> list = service.service();

System.out.println("list.jsp - list : " + list);

//System.out.println("list.jsp - 10/0 : " + (10/0));	//일부러 오류 주는 코드(에러페이지로 이동)
// 로그인 한 경우만 이미지 등록이 가능하도록 한다. - 로그인한 정보는 session에 있다.
//디비 안에 있는 경우와는 상관이 없음.
//LoginVO가 널이면 로그인 안한거, 널이 아니면 로그인을 한거.
LoginVO loginVO = (LoginVO) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트</title>
<style type="text/css">
th, td{
	border: 1px solid #444;
	padding: 5px
}
.dataRow:hover{
	cursor: pointer;
	background: #eee;
}
/* dataRow class(.) 안에 있는 이미지 태그의 사이즈 조정 */
.dataRow img{
	width: 40px;
	height: 45px
}
</style>
</head>
<body>
<h2>이미지 리스트</h2>
<table>
<tr>
	<th>번호</th>
	<th>이미지</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
</tr>
<% for(ImageVO vo : list) { %>
	<tr class="dataRow" onclick="location='view.jsp?no=<%= vo.getNo() %>';">
		<td><%= vo.getNo() %></td>
		<td><img src="<%= vo.getFileName() %>"></td>
		<td><%= vo.getTitle() %></td>
		<td><%= vo.getName() %>(<%= vo.getId() %>)</td>
		<td><%= vo.getWriteDate() %></td>
	</tr>
<% } %>
<tr>
	<td colspan="5">
		<% if(loginVO != null) { %>
			<a href="writeForm.jsp"><button>등록</button></a>
		<% } %>
		<a href="list.jsp"><button>새로고침</button></a>
	</td>
</tr>
</table>
</body>
</html>
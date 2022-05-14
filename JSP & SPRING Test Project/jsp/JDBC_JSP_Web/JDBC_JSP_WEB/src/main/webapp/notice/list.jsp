<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.notice.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.notice.service.NoticeListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
// 데이터 수집
// now - 현재 공지, old - 지난 공지, res - 예약 공지, all - 전체 공지 -> pt(point)
String pt = request.getParameter("pt");
if (pt == null) pt = "now"; // 기본 값 세팅 : 현재 공지

// DB에서 데이터 가져오기 - 생성, 호출
NoticeListService service = new NoticeListService();
List<NoticeVO> list = service.service(pt);

// 데이터 확인하기
System.out.println(list);

// 클릭한 버튼의 스타일 문자열 변수
String style = "background: #660033;color : white;";

// session에서 로그인 정보를 받아낸다.
LoginVO loginVO = (LoginVO) session.getAttribute("login");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>

<style type="text/css">
td, th{
	border: 1px solid #444;
}
th{
	background: black;
	color: white;
}
.dataRow:hover {
	cursor: pointer; /* 손가락 */
	background: #eee;
}
</style>

<script type="text/javascript">
// alert("<%= pt %>"); // 경고창 띄우기
</script>
</head>
<body>
<h2>공지사항 리스트</h2>
<a href="list.jsp?pt=now"><button style='<%= (pt.equals("now"))? style : "" %>'>현재</button></a>
<a href="list.jsp?pt=old"><button style='<%= (pt.equals("old"))? style : "" %>'>지난</button></a>
<a href="list.jsp?pt=res"><button style='<%= (pt.equals("res"))? style : "" %>'>예약</button></a>
<a href="list.jsp?pt=all"><button style='<%= (pt.equals("all"))? style : "" %>'>전체</button></a>
<table>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>시작일</th>
	<th>종료일</th>
	<th>등록</th>
</tr>
<%
if(list == null){
%>
	<tr>
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
<% } else { %>
<% for(NoticeVO vo : list) { %>
	<tr onclick="location='view.jsp?no=<%= vo.getNo() %>'" class="dataRow">
		<td><%= vo.getNo() %></td>
		<td><%= vo.getTitle() %></td>
		<td><%= vo.getStartDate() %></td>
		<td><%= vo.getEndDate() %></td>
		<td><%= vo.getUpdateDate() %></td>
	</tr>
<% } // end of for
} // end of if%>

<% if(loginVO != null && loginVO.getGradeNo() == 9) { %>
	<tr>
		<td colspan="5" style="text-align: center;">
			<a href="writeForm.jsp"><button>등록</button></a>
		</td>
	</tr>
<% } %>
</table>
</body>
</html>
<%@page import="com.webjjang.image.vo.ImageVO"%>
<%@page import="com.webjjang.image.service.ImageViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집 - no가 반드시 넘어와야한다. -> list.jsp부터 실행해 주세요. 제발~~~~~~
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

// Controller(JSP) - service - dao
ImageViewService service = new ImageViewService();
ImageVO vo = service.service(no);

System.out.println("updatForm.jsp - vo : " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 정보 수정 폼</title>
</head>
<body>
<h2>이미지 정보 수정 폼</h2>
<form action="update.jsp" method="post">
<table>
<tr>
	<th>번호</th>
	<td><input name="no" value="<%= vo.getNo() %>" readonly="readonly"></td>
</tr>
<tr>
	<th>제목</th>
	<td><input name="title" value="<%= vo.getTitle() %>"></td>
</tr>
<tr> 
	<th>내용</th>
	<td><textarea rows="7" cols="80" name="content"><%= vo.getContent() %></textarea></td>
</tr>
<tr>
	<td colspan="2">
		<button>수정</button>
		<button type="reset">새로입력</button>
		<button type="button" onclick="history.back()">취소</button>
	</td>
</tr>
</table>
</form>
</body>
</html>
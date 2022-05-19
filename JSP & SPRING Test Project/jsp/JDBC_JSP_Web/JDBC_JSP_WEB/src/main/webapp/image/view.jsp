<%@page import="com.image.vo.ImageVO"%>
<%@page import="com.image.service.ImageViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집 - no가 반드시 넘어와야한다. -> list.jsp부터 실행해 주세요. 제발~~~~~~
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

// Controller(JSP) - service - dao
ImageViewService service = new ImageViewService();
ImageVO vo = service.service(no);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 보기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style type="text/css">
#viewImg{
	width: 400px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

#changeImageDiv{
	display: none;
}

</style>
</head>
<body>
<div class="container">
<h2>이미지 보기</h2>
<table class="table">
<tr>
	<th>번호</th>
	<td><%= vo.getNo() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%= vo.getTitle() %></td>
</tr>
<tr>
	<th>이미지</th>
	<td>
		<img alt="<%= vo.getTitle() %>" src="<%= vo.getFileName() %>" id="viewImg"
		 class="img-rounded" >
		<hr>
		<a href="<%= vo.getFileName() %>" download  class="btn btn-default">다운로드</a>
		<button onclick="$('#changeImageDiv').show()" class="btn btn-default">바꾸기</button>
		<hr>
		<div id="changeImageDiv">
		<form action="changeImage.jsp" method="post" enctype="multipart/form-data">
			<!-- type="hidden" : 사용자는 볼수 없고 데이터는 넘어간다. -->
			<input type="hidden" name="no" value="<%= vo.getNo() %>">
			<input type="hidden" name="oldImage" value="<%= vo.getFileName() %>">
			<input type="file" name="image" required="required">
			<button class="btn btn-default">바꾸기</button>
			<button type="button" onclick="$('#changeImageDiv').hide()" class="btn btn-default">취소</button>
		</form>
		</div>
	</td>
</tr>
<tr>
	<th>내용</th>
	<td><%= vo.getContent() %></td>
</tr>
<tr>
	<th>이름(아이디)</th>
	<td><%= vo.getName() %>(<%= vo.getId() %>)</td>
</tr>
<tr>
	<th>등록일</th>
	<td><%= vo.getWriteDate() %></td>
</tr>
<tr>
	<td colspan="2">
		<a href="updateForm.jsp?no=<%= vo.getNo() %>" class="btn btn-default">정보수정</a>
		<a href="delete.jsp?no=<%= vo.getNo() %>&oldImage=<%= vo.getFileName() %>" 
		 class="btn btn-default">삭제</a>
	</td>
</tr>
</table>
</div>
</body>
</html>
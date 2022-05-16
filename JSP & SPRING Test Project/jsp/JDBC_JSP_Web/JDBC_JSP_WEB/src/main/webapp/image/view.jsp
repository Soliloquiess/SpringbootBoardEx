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
th, td{
	border: 1px solid #444;
	padding: 5px;
}

/* 이미지 크기 설정 */
#viewImg{
	width: 400px;
}

#changeImageDiv{
	display: none;
}

</style>
</head>
<body>
<h2>이미지 보기</h2>
<table>
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
		<img alt="<%= vo.getTitle() %>" src="<%= vo.getFileName() %>" id="viewImg">
		<hr>
		<a href="<%= vo.getFileName() %>" download><button>다운로드</button></a>
		<button onclick="$('#changeImageDiv').show()">바꾸기</button>	<!-- 제이쿼리 사용 -->
		<hr>
<<<<<<< HEAD
		<div id="changeImageDiv"> 
		<form action="changeImage.jsp" method="post" enctype="multipart/form-data">
=======
		<div id="changeImageDiv">
		<form action="changeImage.jsp" method="post" enctype="multipart/form-data">	
		<!-- enctype으로 form-data,  -->
>>>>>>> 145901c80c24388d133b882db9071341a2e73099
			<!-- type="hidden" : 사용자는 볼수 없고 데이터는 넘어간다. -->
			<!-- 아래는 첨부파일에 해당 -->
			<input type="hidden" name="no" value="<%= vo.getNo() %>">
			<input type="hidden" name="oldImage" value="<%= vo.getFileName() %>">
			<input type="file" name="image" required="required">	
			<!-- required로 필수입력하게 만듬 -->
			<button>바꾸기</button>
			<button type="button" onclick="$('#changeImageDiv').hide()">취소</button>
			<!-- 제이쿼리로 changeImageDiv라는 문자열을 찾으면 실행한다. -->
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
		<a href="updateForm.jsp?no=<%= vo.getNo() %>"><button>정보수정</button></a>
		<a href="delete.jsp?no=<%= vo.getNo() %>&oldImage=<%= vo.getFileName() %>"><button>삭제</button></a>
	</td>
</tr>
</table>
</body>
</html>
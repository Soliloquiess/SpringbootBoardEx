
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정</title>


</head>
<body>
<h1>게시판 글 수정 처리</h1>

글번호 : <%=request.getParameter("no") %><br>
<!-- EL객체 사용 - 데이터 표시용. param - 넘어오는 데이터를 의미. 실전에서는 표시하는 건 하지 않는다. -->

제목: ${param.title }<br>
내용: ${param.content }<br>
작성자: ${param.writer }<br>

</div>
</body>
<script type = "text/javascript">
//JS- 5초 후에 글 보기 페이지로 이동시킨다.
setTimeout(()=>{
	location = "view.jsp?no=2";
},5000)

</script>
</html>
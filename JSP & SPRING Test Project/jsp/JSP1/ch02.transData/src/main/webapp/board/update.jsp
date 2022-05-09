<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>

 

</head>
<body>
<div class="container">
<h1>게시판 글수정 처리</h1>
<!-- html로 데이터 입력 받기 -->

글번호 <%=request.getParameter("no") %><br>
<!-- EL객체 사용. 데이터 표시용 , param- 넘어도는 데이터를 의미 -->

제목 : ${param.title};
내용 : ${param.content};
작성자 : ${param.writer};
</div>
</body>

<script type = "text/javascript">
	//JS-5초 후 글보기 페이지로 이동시킨다.
	
	setTimeout(()=>{)
	location = "view.jsp?no=2";
	},5000);
</script>
</html>
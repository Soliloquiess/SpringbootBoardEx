<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!--      isErrorPage="true" 를 주면 예외를 jsp로 전달받고 아래 try catch문으로 처리함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 내부 처리 오류</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="panel panel-danger" style="margin-top : 80px;">
      <div class="panel-heading"><h2>서버 내부 처리 오류</h2></div>
      <div class="panel-body">
      	예외코드 : 500<br>
      	요청 URL : <%=request.getServletPath() %>	
      	<!-- getServletPath가 url -->
      	<!-- exception 객체를 사용하려면 맨위에 page 디렉티브 태그에서 속성으로 isErrorPage="true" 지정해야 한다. -->
      	예외메시지 : <%= (exception.getMessage() != null) ? exception.getMessage() :exception.getCause() %>
      </div>
      <div class="footer">
      	<a href="/board/list.do" class="btn btn-success">게시판(main으로 바꾼다.)</a>
      </div>
	</div>
</div>
</body>
</html>
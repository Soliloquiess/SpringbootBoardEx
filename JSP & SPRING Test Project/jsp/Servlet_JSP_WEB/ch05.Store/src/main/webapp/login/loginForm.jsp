<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>로그인 폼</h1>
<form action="login.jsp" method="post">
  <div class="form-group">
    <label for="id">아이디</label>
    <input type="text" class="form-control" id="id" name="id">
  </div>
  <div class="form-group">
    <label for="pw">비밀번호</label>
    <input type="password" class="form-control" id="pw" name= "pw">
  </div>
  <button type="submit" class="btn btn-default">로그인</button>

</form>
</div>
</body>
</html>
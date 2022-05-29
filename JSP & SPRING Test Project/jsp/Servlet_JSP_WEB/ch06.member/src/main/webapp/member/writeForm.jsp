<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type = "text/javascript">
$(function(){
	$("#idCheckBtn").click(function(){
		//window.open = window객체로 창을 여는 메서드
		//open(url, name, option)
		window.open("idCheck.jsp","idCheck", "width = 400 ,height = 400, top =100, left=400")
	})
});
</script>
</head>
<body>
	<div class="container">
		<h1>회원가입</h1>
		<!-- 아이디 , 비밀번호, 이름, 성별, 생년월일, 연락처, 이메일, 사진(나중에) -->
		<form action="write.jsp" method="post">
			<!-- div 클래스 분할  tag: 특정한 영역을 지정한다.-->
			<div class="form-group">
				<label for="id">아이디</label> 
				<input name="id" id="id"
					required="required" class="form-control" readonly="readonly"
					placeholder="아이디 중복체크 하세요">
				<button type="button" id = "idCheckBtn" class = "btn btn-default">아이디 중복 체크</button>
			</div>
		</form>
	</div>
</body>
</html>
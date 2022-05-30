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

<script type="text/javascript">
	$(function() {
		$("#idCheckBtn").click(
				function() {
					//window.open = window객체로 창을 여는 메서드
					//open(url, name, option)
					window.open("idCheck.jsp", "idCheck",
							"width = 400 ,height = 400, top =100, left=400")
				})
	});
</script>
</head>
<body>
	<div class="container">
		<h1>회원가입</h1>
		<!-- 아이디 , 비밀번호, 이름, 성별, 생년월일, 연락처, 이메일, 사진(나중에) -->
		<!-- 사진 파일을 업로드 하기 위해서 method = "post", enctype = "multipart/form-data"속성 지정 -->
		<form action="write.jsp" method="post" enctype="multipart/form-data">
			<!-- div 클래스 분할  tag: 특정한 영역을 지정한다.-->
			<div class="form-group">

				<label for="id">아이디</label>

				<div class="input-group">

					<input name="id" id="id" required="required" class="form-control"
						readonly="readonly" placeholder="아이디 중복체크를 이용하세요. 팝업을 허용하셔야 합니다.">

					<div class="input-group-btn">

						<button type="button" id="idCheckBtn" class="btn btn-default">아이디
							중복체크</button>

					</div>

				</div>

			</div>

			<!-- <div class="form-group">
				<label for="id">아이디</label> <input name="id" id="id"
					required="required" class="form-control" readonly="readonly"
					placeholder="아이디 중복체크 하세요">
				<div class="input-group-btn">

					<button type="button" id="idCheckBtn" class="btn btn-default">아이디
						중복체크</button>

				</div>
			</div> -->


			<div class="form-group">

				<label for="pw">비밀번호</label> <input name="pw" type="password"
					class="form-control" id="pw">

			</div>

			<div class="form-group">

				<label for="pw2">비밀번호 확인</label> <input type="password"
					class="form-control" id="pw2">

			</div>

			<div class="form-group">

				<label for="name">이름</label> <input name="name" type="text"
					class="form-control" id="name">

			</div>

			<div class="form-group">

				<label>성별</label>

				<div class="form-inline">

					<!-- style - margin : 바깥쪽 여백 -->

					<div class="form-group" style="margin: 0 10px">

						<label> <input name="gender" type="radio"
							class="form-control" id="gender_man" value="남자" checked="checked">

							남자

						</label>

					</div>

					<div class="form-group">

						<label> <input name="gender" type="radio"
							class="form-control" id="gender_woman" value="여자"> 여자

						</label>

					</div>

				</div>

			</div>

			<div class="form-group">

				<label for="birth">생년월일</label> <input name="birth" type="date"
					class="for-control" id="birth">

			</div>

			<div class="form-group">

				<label>연락처</label>

				<div class="form-inline">

					<input name="tel" class="form-control" style="width: 60px"
						type="text" maxlength="3" pattern="[0-9]{2,3}"
						title="숫자 2 ~ 3자리를 입력할 수 있습니다.">- <input name="tel"
						class="form-control" style="width: 80px" type="text" maxlength="4"
						pattern="[0-9]{4}" title="숫자 4자리를 입력할 수 있습니다.">- <input
						name="tel" class="form-control" style="width: 80px" type="text"
						maxlength="4" pattern="[0-9]{4}" title="숫자 4자리를 입력할 수 있습니다.">

				</div>

			</div>

			<div class="form-group">

				<label for="email">이메일</label>

				<!-- type : email - 모바일의 키패드가 email입력 패드로 바뀐다. 입력한 데이터가 이메일 양식에 맞는지 검사한다. -->

				<input name="email" class="form-control" type="email" id="email">

			</div>

			<div class="form-group">

				<label for="photo">사진</label> <input name="photo" type="file"
					id="photo" class="form-control">

			</div>

			<button class="btn btn-default">가입</button>

			<button class="btn btn-default" type="reset">새로고침</button>

			<button class="btn btn-default" type="button" id="cancelBtn">취소</button>

		</form>
	</div>
</body>
</html>
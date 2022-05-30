<%@page import="com.member.service.MemberIDCheckService"%>

<%@page import="com.main.controller.ExecuteService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// 넘어오는 데이터 수집

String id = request.getParameter("id");

// id가 null이 아니면 db에서 id를 가져와 본다. 가져온 id를 request에 담는다.

request.setAttribute("id", ExecuteService.execute(new MemberIDCheckService(), id));
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>아이디 중복 체크</title> ​

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

​ ​

<script type="text/javascript">
	$(function() {

		$("#useIdBtn").click(function() {

			// 회원가입 창의 id에 찾은 데이터를 셋팅한다.

			// opener - 나를 열게한 창

			// opener.document - 나를 열게한 찬의 문서(HTML)

			$(opener.document).find("#id").val("${param.id}");

			// 창닫기를 한다.

			window.close();

		});

	});
</script>

</head>

<body>

	<div class="container">

		<h1>아이디 중복 체크</h1>

		<!-- hr - 수평선 그리기 태그 -->

		<hr>

		<form action="idCheck.jsp" method="post">

			<div class="form-group">

				<label>아이디 입력</label> <input name="id" class="form-control">

			</div>

			<button class="btn btn-default">체크</button>

		</form>

		<hr>

		<div id="result">

			<c:if test="${empty param.id }">

				<!-- 데이터 아이디가 안 넘어온 경우 -->

아이디를 입력해 주세요. 그리고 체크 버튼을 클랙하세요.

</c:if>

			<c:if test="${!empty param.id }">

				<!-- 데이터가 넘어온 경우 -->

				<c:if test="${!empty id }">

					<!-- 아이디가 있는 경우 - 중복되었지 때문에 사용 불가 -->

검색한 아이디 [<span style="color: red">${id }</span>]는 중복된 아이디 입니다. 다시 검색해 주세요.

</c:if>

				<c:if test="${empty id }">

					<!-- 아이디가 없는 경우 - 사용 가능한 아이디 -->

검색한 아이디 [<span style="color: red">${param.id }</span>]는 사용가능한 아이디 입니다. 

<button class="btn btn-default" id="useIdBtn">아이디 사용하기</button>

				</c:if>

			</c:if>

		</div>

	</div>

</body>

</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}"/>
<title>Insert title here</title>
<style type="text/css">
	.star_rating{
		margin: 15px 0 35 px;
		font-size: 0;
		text-align: center;
	}
	
	.star_rating .ico_star{
		display:inline-block;
		width: 30px;
		height: 30px;
		background: url("${context}/resources/img/ico_star.png");
		text-align: -9999px;
		background-size: 30px 30px;
		vertical-align: top;
		cursor: pointer;
	}
	
	.star_rating .ico_star.is_selected{
		background-image: url("${context}/resources/img/ico_star_on.png");
		
	}
	
	.star_rating .ico_star+.ico_star{
		margin-left: 10px;
	}
	
</style>
</head>
<body>

	<div>
		<div>
			<strong>별점주기</strong>
			<div class="star_rating">
				<span class="ico_star is_selected">1</span>
				<span class="ico_star is_selected">2</span>
				<span class="ico_star is_selected">3</span>
				<span class="ico_star is_selected">4</span>
				<span class="ico_star is_selected">5</span>
			</div>
		</div>
	</div>
</body>
</html>
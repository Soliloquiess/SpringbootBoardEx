<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>

<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		// tag안에 data-module ="데이터" 로 설정한 데이터 가져오기
		var module = $(this).data("module");
		var no = $(this).find(".no").text();
		// 글보기 URL
		var url = "/" + module + "/view.do?no=" + no;
		if(module == "board") url += "&inc=1";
		alert(url);
		location = url;
	});
});
</script>

</head>
<body>
<div class="container">
	<!-- 공지사항과 일반 게시판 -->
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">공지사항</div>
			  <div class="panel-body">
					<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
					<table class="table">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>시작일</th>
					</tr>
					
						<!-- c:forEach : JSTL - 반복문 items = "여러개의 데이터" var = "변수"
							 begin = "시작번호" end = "끝번호" step = "증감값"
							 varStatus = "반복회수나 인덱스 해당되는 정보가 있는 변수"
							 list의 데이터는 서버 저장 객체중 어디에 있을까요? request -->
						<c:forEach items="${noticeList }" var="vo">
							<!-- tr 객체를 쉽게 선택할 수 있도록하기 위한 class 지정 -->
							<tr class="dataRow" data-module="notice">
								<!-- vo의 데이터는 서버 저장 객체중 어디에 있을까요? pageContext
									EL 객체는 pageContext => request => session => application에 key가지고 있는 데이터 표시 -->
								<td class="no" >${vo.no }</td>
								<td >${vo.title }</td>
								<td >${vo.startDate }</td>
							</tr>
						</c:forEach>
			  		</table>
			  </div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">게시판</div>
			  <div class="panel-body">
			  
				  <table class="table">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
					</tr>
					<!-- c:forEach : JSTL - 반복문 items = "여러개의 데이터" var = "변수"
						 begin = "시작번호" end = "끝번호" step = "증감값"
						 varStatus = "반복회수나 인덱스 해당되는 정보가 있는 변수"
						 list의 데이터는 서버 저장 객체중 어디에 있을까요? request -->
					<c:forEach items="${boardList }" var="vo">
						<!-- tr 객체를 쉽게 선택할 수 있도록하기 위한 class 지정 -->
						<tr class="dataRow" data-module="board">
							<!-- vo의 데이터는 서버 저장 객체중 어디에 있을까요? pageContext
								EL 객체는 pageContext => request => session => application에 key가지고 있는 데이터 표시 -->
							<td class="no" >${vo.no }</td>
							<td >${vo.title }</td>
							<td >${vo.writer }</td>
						</tr>
					</c:forEach>
					
				  </table>
				  
			  </div>
			</div>
		</div>
	</div>
	<!-- 이미지 게시판 -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
			  <div class="panel-heading">이미지</div>
			  <div class="panel-body">
			      <!-- 이미지 데이터가 있는 만큼 반복 처리 -->
				  <c:forEach items="${imageList }" var="vo">
				  <!-- 하나의 이미지 표시
				  		BS grid : 한 줄을 전체 12로 나눈다. class="col-md-3" : col-해상도-칸 : 칸을 더해서 12를 넘기지 못한다. -->
				  <div class="col-md-3">
				  	<!-- BS image : class="thumbnail" | img-rounded | img-circle
				  		div class를 이미지로 지정해서 이미지와 글자를 셋트로 디자인시킨다. 이미지 태그에 적용할 수도 있다. -->
				    <div class="thumbnail dataRow" data-module = "image">
				        <img src="${vo.fileName }" alt="${vo.title }" style="width:100%">
				        <div class="caption">
				          <p>[<span class="no">${vo.no }</span>] ${vo.title }</p>
				        </div>
				    </div>
				  </div>
				  </c:forEach>
			  
			  </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
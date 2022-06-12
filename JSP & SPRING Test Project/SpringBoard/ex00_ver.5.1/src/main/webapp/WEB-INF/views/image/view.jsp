<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 보기</title>
<!-- tooltip 적용 시 초기화? -> 적용 : 라이브러리(bootstrap.min.js)를 2번 등록시켜서 해결한다. -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
	#updateMsgDiv {
		display: none;	/* <!-- 맨 처음에는 안 보이게 설정 --> */
	}
	#changeImageDiv{
		display: none;
	}
</style>
<script type="text/javascript">
	$(function(){
		// 객체의 너비 구하기 - 객체.width() - 단위가 없는 숫자가 나온다.
		// 객체의 너비 조정하기 - 객체.width(너비값) - 너비값은 단위를 반드시 붙여야만 한다.
		// 이미지 너비
		var imageWidth = $("#image").width();
		var imageDivWidth = $("#imageDiv").innerWidth();
// 		alert("이미지의 너비 : " +imageWidth + "\n이미지 Div의 너비 : " + imageDivWidth);
		// 이미지의 너비가 크면 줄여야 한다.
		if(imageDivWidth < imageWidth) $("#image").width("100%"); // div너비와 맞추려면 imageDivWidth + "px"
		
		// 수정 버튼에 메시지는 처음에는 안보이게 한다. -> style - display : none
		// 수정 버튼에 마우스가 올라가면
		$("#updateBtn")
		.mouseover(function(){
			//alert("수정 버튼에 마우스 올라감");
// 			$("#updateMsgDiv").show();
			$("#updateMsgDiv").slideDown();
// 			$("#updateMsgDiv").toggle();
		})
		.mouseout(function(){
			//alert("수정 버튼에 마우스 올라감");
// 			$("#updateMsgDiv").hide();
			$("#updateMsgDiv").slideUp();
// 			$("#updateMsgDiv").toggle();
		})
		;
		
		// 이미지 바꾸기 이벤트
		$("#changeImageBtn").click(function(){
			//alert("이미지 바꾸기");
			$("#changeImageDiv").slideToggle();
		});
		
		// 삭제 버튼 경고 이벤트
		$("#deleteBtn").click(function(){
			// alert("삭제 갑니다.");
			// a tag의 페이지 이동을 무시 시킨다.
			// confirm() -> 사용자에게 확인이나 취소를 누르게 하는 함수 -> 확인 - true / 취소 - false
			if(!confirm("보고 계신 이미지를 정말 삭제하시겠습니까?"))	return false;
		});
		
	});
</script>
</head>
<body>
<div class= "container">
	<h2>이미지 보기</h2>
	<div class="well row">
		<div class="col-sm-3">번호</div>
		<div class="col-sm-9">${vo.no }</div>
	</div>
	<div class="well row">
		<div class="col-sm-3">제목</div>
		<div class="col-sm-9">${vo.title }</div>
	</div>
	<div class="well row">
		<div class="col-sm-3">
			<div>
				이미지 <button class="btn btn-warning btn-sm" id="changeImageBtn">바꾸기</button>
			</div>
			<div id="changeImageDiv">
				<form action="changeImage.do" method="post" enctype="multipart/form-data">
					<input name="no" type="hidden" value="${vo.no }">
					<input name="deleteImage" value="${vo.fileName }" type="hidden">
					<input type="file" name="image" class="form-control">
					<button class="btn btn-default">바꾸기</button>
				</form>
			</div>
		</div>
		<div class="col-sm-9" id="imageDiv"><img src="${vo.fileName }" class="thumbnail"  id="image"></div>
	</div>
	<div class="well row">
		<div class="col-sm-3">내용</div>
		<div class="col-sm-9">${vo.content }</div>
	</div>
	<div class="well row">
		<div class="col-sm-3">작성자</div>
		<div class="col-sm-9">${vo.name }(${vo.id })</div>
	</div>
	<div class="well row">
		<div class="col-sm-3">작성일</div>
		<div class="col-sm-9"><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></div>
	</div>
	<!-- tooltip을 사용하려면 js에서 $('[data-toggle="tooltip"]').tooltip(); 추가해야 한다. -->
	<a href="update.do?no=${vo.no }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
	 class = "btn btn-default" id="updateBtn" data-toggle="tooltip" title="이미지 바꾸기는 이미지 제목 오른쪽의 바꾸기 버튼을 이용하세요."
	  >수정</a>
	<a href="delete.do?no=${vo.no }&deleteImage=${vo.fileName}"
	 class = "btn btn-default" id="deleteBtn">삭제</a>
	<a href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
	 class = "btn btn-default">리스트</a>
	<div class="alert alert-info" id="updateMsgDiv">이미지 바꾸기는 이미지 제목 오른쪽의 바꾸기 버튼을 이용하세요.</div>
</div>
</body>
</html>
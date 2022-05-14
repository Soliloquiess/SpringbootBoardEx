<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문하기 폼</title>
<!-- emptyCheck(objStr, itemName) 함수 있는 js 파일을 포함시킨다. -->
<script type="text/javascript" src="/js/formUtil.js"></script>

<style type="text/css">
/* Bootstrap CSS 때문에 적용이 되지 않는다. -> 태그에 style 속성으로 지정한다. js에서 처리한다. */
input, textarea{
	background: #eee;
}
</style>

<script type="text/javascript">
// jQuery에서 문서가 로딩이 다 되면 처리되는 구조
$(function(){
	
	// 입력란의 배경색을 #eee(옅은 회색) 만들어 놓고, 입력하는 입력란에 배경색을 흰색으로 해보자.
	// jQuery
	// input 태그와 textarea 태그를 선택 + css 지정
	$("input, textarea").css("background","#eee")
	// 선택한 객체의 focus - 커서가 들어갔을 때 이벤트 처리
	.focus(function(){
		console.log("입력란 들어옴.");
		$(this).css("background","#fff");
	})
	// 선택한 객체의 blur - 커서가 나갈 때 이벤트 처리
	.blur(function(){
		console.log("입력란 나감.");
		$(this).css("background","#eee");
	});
	
	$("#writeForm").submit(function(){
		// alert("데이터를 넘기려고 한다.");
		
		// 필수입력 항목 검사 - JS
		// 제목 -> 비어있으면 경고>포커스>이동막기
		if(emptyCheck("#title", "제목")) return false;
		// 내용 -> 비어있으면 경고>포커스>이동막기
		if(emptyCheck("#content", "내용")) return false;
		
		// 길이 제한 검사 - JS
		// 제목은 4~100 까지 사용가능
		if(!lengthCheck("#title", "제목", 4, 100)) return false;
		// 내용은 4~ 666(2000 바이트) 까지 사용가능
		if(!lengthCheck("#content", "내용", 4, 666)) return false;
		
		// 코딩이 아직 안 끝나서 임시적으로 submit되는 것을 막는다.
		// return false;
	});
});
</script>
</head>
<body>
<div class="container">
<h2>질문하기 폼</h2>
<!-- 사용자에게 데이터를 입력하도록 한다. : form, input, select, textarea tag -->
<!-- action : 데이터를 받을 URL, method : get - URL 뒤에 데이터, post - 따로 보이지 않게 데이터
	 Bootstrap - class="form-horizontal" : 한줄에 라벨과 입력을 같이 둔다.
	 col-sm-2 : col - 칸, sm - 해상도, 2 - 너비(총12까지 사용) -->
<form action="write.jsp" method="post" class="form-horizontal" id="writeForm" >
		<div class="form-group">
			<label for="title" class="control-label col-sm-2" >제목</label>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<div class="col-sm-10">
				<input type="text" name="title" maxlength="100" id="title" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="control-label col-sm-2" >내용</label>
			<div class="col-sm-10">
				<textarea rows="7" name="content" id="content" class="form-control"></textarea>
			</div>
		</div>
		<div class="text-center">
			<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">다시 입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
</form>
</div>
</body>
</html>
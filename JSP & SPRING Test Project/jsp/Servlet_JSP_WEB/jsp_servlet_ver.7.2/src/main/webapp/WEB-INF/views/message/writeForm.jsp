<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보내기 폼</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 : sitemesh에서 처리 -->

<script type="text/javascript">
// jquery()
$(function(){
	// 이벤트 처리
	// jquery 에서 선택 - tr : tag, .dataRow : class, #cancelBtn : id
	$("#cancelBtn").click(function(){
		// alert("취소 클릭");
		// history - 방문한 페이지의 정보가 저장되어 있는 객체 : JS
		// history.back() - 이전 페이지로 이동. histoty.go(-1)
		history.back();
	})
});
</script>

</head>
<body>
<div class="container">
<h1>메시지 보내기 폼</h1>
<!-- html로 데이터 입력 받기 -->
<form action="write.do" method="post">
<div class="form-group">
	<label for="accepter">받는 분 아이디</label>
	<input name="accepter" class="form-control" required id="accepter">
</div>
<div class="form-group">
	<label for="content">내용</label>
	<textarea rows="7" name="content" class="form-control" required id="content"></textarea>
</div>
<!-- button의 기본 tpye - submit ==> button을 클리하면 submit()를 호출해서 데이터와 함께 연결된 페이지로 이동 -->
<button>보내기</button>
<button type="reset">새로입력</button>
<!-- type="button" - 버튼으로 보이지만 동작을 하지 않는다. 동작을 JS로 작성한다. -->
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>
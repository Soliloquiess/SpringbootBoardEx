<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<%
// java / jsp
// 데이터를 가져오는 것은 BoardController에서 해결했다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 리스트</title>

  <!-- bootstrap 라이브러리 CDN 방식 등록 - default_decorator.jsp에서 작성 -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!-- css - 꾸미기 -->
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>

<script type="text/javascript">
// jquery ( == $)  - HTML의 로딩이 끝나면 동작
$(function(){
	// 데이터 한 줄을 클릭하면 글번호, inc와 함께 글보기로 이동한다.
	$(".dataRow").click(function(){
		//alert("글보기 이동 클릭");
		var no = $(this).find(".no").text();
		// var no1 = $(this).data("no"); // data-no의 설정 데이터 가져오기
		// alert(no + "/" + no1);
		// js : 페이지 이동 location.href = location
		location = "view.do?no=" + no
				+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
				+ "&key=${pageObject.key}&word=${pageObject.word}";
	});
});
</script>

</head>
<body>
<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<div class="container">
<h1>이미지 게시판 리스트</h1>

<!-- 게시판 검색 -->
<div>
	<form action="list.do">
		<!-- 페이지 정보를 다시 그대로 보내준다. -->
		<input name="page" type="hidden" value="1">
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
		<div class="input-group">
			<span class="input-group-addon">
				<!-- 검색 종류 선택 (pulldown 메뉴 - select) : key -->
				<select name="key">
					<option value="t" ${(pageObject.key == "t")?"selected":"" }>제목</option>
					<option value="c" ${(pageObject.key == "c")?"selected":"" }>내용</option>
					<option value="w" ${(pageObject.key == "w")?"selected":"" }>작성자</option>
					<option value="tc" ${(pageObject.key == "tc")?"selected":"" }>제목/내용</option>
					<option value="tw" ${(pageObject.key == "tw")?"selected":"" }>제목/작성자</option>
					<option value="cw" ${(pageObject.key == "cw")?"selected":"" }>내용/작성자</option>
					<option value="tcw" ${(pageObject.key == "tcw")?"selected":"" }>모두</option>
				</select>
			</span>
			<input id="word" type="text" class="form-control" name="word" placeholder="검색 문장"
			value="${param.word }">
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		</div>
		
	</form>
</div>

 <div class="row">
  <!-- 이미지 데이터가 있는 만큼 반복 처리 -->
  <c:forEach items="${list }" var="vo" varStatus="vs">
  <c:if test="${vs.index != 0 && vs.index % 4 == 0 }">
  	<!-- 이미지 인덱스가 0보다 크면서 4배의 배수이면 줄바꿈 코드를 추가한다. -->
  	${"</div><div class='row'>"}
  </c:if>
  <!-- 하나의 이미지 표시 -->
  <div class="col-md-3">
    <div class="thumbnail dataRow" data-no = "${vo.no }">
        <img src="${vo.fileName }" alt="${vo.title }" style="width:100%">
        <div class="caption">
          <p>[<span class="no">${vo.no }</span>] ${vo.title }</p>
          <div>${vo.name }(${vo.id }) - ${vo.writeDate }</div>
        </div>
    </div>
  </div>
  </c:forEach>
</div>

<!-- 페이지네이션 -->
<div>
<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" 
 query="&key=${pageObject.key }&word=${pageObject.word }" />
</div>

<!-- html tag 안에 클래스를 이용한 디자인 : BootStrap -->
<a href="writeForm.do" class="btn btn-default">글쓰기</a>
</div>
</body>
</html>
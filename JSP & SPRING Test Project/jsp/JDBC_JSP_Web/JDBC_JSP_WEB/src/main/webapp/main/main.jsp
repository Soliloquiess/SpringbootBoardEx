<%@page import="com.board.vo.BoardVO"%>
<%@page import="com.board.service.BoardListService"%>
<%@page import="com.notice.vo.NoticeVO"%>
<%@page import="com.notice.service.NoticeListService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="java.util.List"%>
<%@page import="com.util.PageObject"%>
<%@page import="com.image.service.ImageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// PageObject 생성
PageObject pageObject = new PageObject();
// 데이터 가져오기 - 이미지 리스트(3개), 공지사항 리스트(5개), 게시판 리스트(5개)
// 이미지 데이터 가져오기
ImageListService imageListService = new ImageListService();
// 데이터의 갯수를 3개로 정한다.
pageObject.setPerPageNum(3);
List<ImageVO> imageList = imageListService.service(pageObject);
// 공지사항 데이터 가져오기
NoticeListService noticeListService = new NoticeListService();
// 데이터의 갯수를 5개로 정한다.
pageObject.setPerPageNum(5);
List<NoticeVO> noticeList = noticeListService.service(pageObject);
// 게시판 데이터 가져오기
BoardListService boardListService = new BoardListService();
// 위에서 데이터 개수를 5로 정해놨다.
List<BoardVO> boardList = boardListService.service(pageObject);

// EL이나 JSTL을 사용하려면 서버 기본 저장 객체에 담아야 한다.
request.setAttribute("imageList", imageList);
request.setAttribute("noticeList", noticeList);
request.setAttribute("boardList", boardList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style type="text/css">
.imageDataRow:hover, .noticeDataRow:hover, .boardDataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>
<script type="text/javascript">
$(function(){
	$(".imageDataRow, .noticeDataRow, .boardDataRow").click(function(){
		//alert("click");
		var no = $(this).data("no");
		var url = "/";
		if($(this).hasClass("imageDataRow")) url = url + "image";
		if($(this).hasClass("noticeDataRow")) url = url + "notice";
		if($(this).hasClass("boardDataRow")) url = url + "board";
		url += "/view.jsp?no=" + no + "&inc=1";
		location = url;
	});
});
</script>
</head>
<body>
<div class="container">
	<div class="well">
		<h2>웹짱커뮤니티</h2>
	</div>
	<div class="row">
		<div class="col-md-12">
			<jsp:include page="/include/imageList.jsp" />
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<jsp:include page="/include/noticeList.jsp" />
		</div>
		<div class="col-md-6">
			<jsp:include page="/include/boardList.jsp" />
		</div>
	</div>
</div>
</body>
</html>
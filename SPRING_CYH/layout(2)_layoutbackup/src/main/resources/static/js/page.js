/**
 * 
 */


$(document).ready(function() {
	/*	
	var pageNum = "[[${page.pageNum}]]";
	var rowsPerPage = "[[${page.rowsPerPage}]]";
	var totalCount = "[[${page.totalCount}]]";
	var keyword = "[[${page.keyword}]]";
	var pageCount = "[[${page.pageCount}]]";
	*/
	
	var pageNum = $("#page-pageNum").val();
	var rowsPerPage = $("#page-rowsPerPage").val();
	var totalCount = $("#page-totalCount").val();
	var keyword = $("#page-keyword").val()	;
	var pageCount = $("#page-pageCount").val();
	
	
	// 페이지당 게시글 수 초기화
	$("#rowsPerPage option[value=" + rowsPerPage + "]").attr("selected", "selected");
	
	// 노출 페이지 수 초기화
	$("#pageCount option[value=" + pageCount + "]").attr("selected", "selected");
	
	
	// 줄 보기 변경 이벤트
	$('#rowsPerPage').on('change', function() {
		rowsPerPage = $(this).val();
		location.href = "/board/list?pageNum=" + 1 
								 + "&totalCount=" + totalCount 
								 + "&keyword=" + keyword
								 + "&rowsPerPage=" + rowsPerPage
								 + "&pageCount=" + pageCount;
	});
	
	// 노출 페이지 개수 변경 이벤트
	$('#pageCount').on('change', function() {
		pageCount = $(this).val();
		location.href = "/board/list?pageNum=" + 1 
								 + "&totalCount=" + totalCount 
								 + "&keyword=" + keyword
								 + "&rowsPerPage=" + rowsPerPage
								 + "&pageCount=" + pageCount;
	});
	
});
	

	
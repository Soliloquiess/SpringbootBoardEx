
 var header = $("meta[name='_csrf_header']").attr("content");
 var token = $("meta[name='_csrf']").attr("content");
              

$(function() {
		var form = $("#board");
		var board_no = $("#boardNo").val();
		
		//댓글 목록 조회 요청
			
		replyList(board_no);
		
		// 등록 버튼 클릭 이벤트
			$("#btnRegister").on("click", function() {
				form.attr("action", "/board/register");
				form.attr("method", "post");
				form.submit(); 
			});
		
		
		
		//답장버튼 클릭 이벤트
		$("#btnAnswer").on("click", function() {
			self.location = "/board/answer?groupNo=" + board_no;
		})
		
		
		
		//수정버튼 클릭 이벤트
		$("#btnModify").on("click", function() {
			self.location = "/board/modify?boardNo=" + board_no;
		})
		
		//
		$("#btnUpdate").on("click", function() {
			form.attr("action", "modify");
			form.submit();
		})

		//삭제버튼 클릭 이벤트
		$("#btnRemove").on("click", function() {
			
			form.submit();
		});

		//목록 버튼 클릭 이벤트
		$("#btnList").on("click", function() {
			self.location = "/board/list";
		});
		// 댓글 등록 버튼 클릭 이벤트
		$("#btnReplyRegister").on('click', function() {
			var reply_writer = $('#replyWriter').val();
			var reply_content = $('#replyContent').val();
			writeReply(board_no, reply_writer, reply_content); //함수 만들어서 ajax 요청
		});
	
		//답글 등록 버튼 클릭 이벤트
		$("#btnAnswerRegister").on('click', function(){
			form.attr("action", "/board/answerRegister");
			form.submit();
		});	
	});
	
	
	
	

	

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css"> -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
  .ui-autocomplete {
    max-height: 100px;
    overflow-y: auto;
    overflow-x: hidden;
  }
</style>
<script>
/*
$(function () {
    var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C ++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];
    $ ( "#tags").autocomplete ({
      source: availableTags
    });
  });*/
  
  $(document).on('focus',"#sentence",function() {
		$("#sentence").autocomplete({
			source : function(request, response) { // source는 자동완성 할 대상
				var autocom = $.ajax({ 
					type : 'post',
					url : "./ajax/auto",
					dataType : "json",
					data : {
						value : request.term
					},
					success : function(result) {
						console.log(result);
						// 서버에서 json 데이터 response 후 목록에 뿌려주기 위함
						data = JSON.parse(result.resMsg);
						response($.map(data, function(item) {
							return {
								label : item.data,
								value : item.data
							}
						}));
						var auto_width = parseInt($('.ui-autocomplete').css('top'));
						//새로운 변수에 연산 후 보관 // .ui-autocomplete는 제이쿼리 자동완성 기능이다.
						auto_width = auto_width - 1;
						$('.ui-autocomplete').css('top',auto_width+'px');
						$('.ui-autocomplete').css('left','1px'); 
						$('.ui-autocomplete').css('max-height','156px');  //자동완성 높이
						$('.ui-autocomplete').css('overflow-style','none'); //IE scroll display none
						$('.ui-autocomplete').css('overflow-y','auto'); //스크롤 세로기능 열기
						$('.ui-autocomplete').css('overflow-x','hidden'); //스크롤 가로기능 열기
					}
				});
			},
			minLength : 1,
			//maxResults : 5,
			//autoFocus:true,
			select : function(event, ui) { // select는 item 선택시 이벤트
				sessionCheck(ui.item.label);
				$("#sentence").val("");
				$('.ui-autocomplete').css('display','none');
				return false;
			},
			focus : function(event, ui) { // 포커스시 이벤트
				return false;
			},
			position : { // 위치 식별
				my : "left bottom",
				at : "left top",
				collision : "flip"
			},
			open: function(event, ui) {
		        $('.ui-autocomplete').off('menufocus hover mouseover mouseenter');
		    },
		    classes:{ // ui 클래스 이름
		    	"ui-autocomplete": "keyword_box"
		    }
		    //,
		    //appendTo : '.auto_keyword'
		});
		$.ui.autocomplete.prototype._renderItem = function(ul, item) {
			//console.log('ul '+ul);
			//console.log('item ' + item);
			//console.log(this.term);
			
			// IE 스크롤 이동 오류로 append 위치 변경
			ul.appendTo($('.auto_keyword'));
			var t = String(item.value).replace(new RegExp(this.term, "i"),
					"<strong>$&</strong>"); //검색어 강조부분
			return $("<li></li>").data("item.autocomplete", item).append("<a href=\"#\" class=\"sch_keyword\">" + t + "</a>").appendTo(ul);
		};
		//$('.ui-autocomplete').css('top', '609px!important');
		
	});
</script>
</head>
<body>
<!-- 
<div class="ui-widget">
	<label for="tags">tags:</label>
	<input id="tags">
</div> -->

<div class="auto_keyword">
	<input id="sentence">
</div>


</body>
</html>
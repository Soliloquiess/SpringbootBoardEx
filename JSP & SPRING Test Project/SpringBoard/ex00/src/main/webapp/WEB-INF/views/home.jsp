<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<script src="http://code.jquery.com/jquery-1.7.js"	type="text/javascript"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
<html>

<style>
.ui-autocomplete { 
    position: absolute; 
    cursor: default; 
    height: 200px; 
    overflow-y: scroll; 
    overflow-x: hidden;}
</STYLE>

<script type="text/javascript">

$(document).ready(function() {

	$("#testText").autocomplete({
		
		source : function(request, response) {

			$.ajax({

				url : "/autocomplete",
				type : "post",
				dataType : "json",
				data: { term : request.term, param1 : "param1 Value", param2 : "param2 Value"},
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
				data : request,

				success : function(data) {

					var result = data;

					response(result);

				},

				error : function(data) {

					alert("에러가 발생하였습니다.")

				}
			});
		}
	});
});



</script>
<body>
	
	<form id="testform" name="testform" align="center">
		<input type="text" id="testText" placeholder="직원 이름을 입력하세요">
		<input type="button" value="Click" class="button" />
	</form>
	
</body>
</html>

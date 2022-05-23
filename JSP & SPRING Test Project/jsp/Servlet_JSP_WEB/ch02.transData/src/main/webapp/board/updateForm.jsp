<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
long no = Long.parseLong(request.getParameter("no")); //no를 가져와서 no의 이름을 가진 변수에에 넣어줌. 그리고 넣을 때 lLong 객체로 파싱해서 넣어줌.(long에 들어가도록)
//나머지는 no에 해당되는 데이터를 DB에서 가져온다.

String title = "java";
String content = "java content";
String writer = "lee";
%>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>

</head>
<body>
		<h1>게시판 글쓰기 폼</h1>


		<!-- html로 데이터 입력 받기 -->
		<form action="update.jsp" method="post">
			<!--  form은 서버 데이터 넘길떄 쓰고 클릭하면 데이터가 전달 되면서 페이지 이동하는 html from tag

	입력 데이터 태그 : <input> , <textarea>, <select>
	입력 방식 : get - 데이터가 주소 뒤에 붙는다. post = 보이지 않는다. -->
			<!-- write.jsp?title=java -->
			글번호: <input name = "no" value = "<%= no %>" readonly = "readonly"><br>
			제목: <input name="title" value = "<%=title%>" required = "required" pattern = .{4,20} title="글 제목을 4글자 이상 100글자 아래로 줘야합니다."><br>
			<!-- //제목 필수에 패턴으로 4글자 이상 20글자 미만으로 제약을 준다. --> 
			내용: <textarea rows="5" cols="80" name="content"><%=content %></textarea> <br> 
			작성자 : <input name="writer" value = "<%=writer%>"><br>
			<button>수정</button>
			
<!-- 			http://localhost/board/updateForm.jsp?no=2 -->

		</form>
</body>
</html>
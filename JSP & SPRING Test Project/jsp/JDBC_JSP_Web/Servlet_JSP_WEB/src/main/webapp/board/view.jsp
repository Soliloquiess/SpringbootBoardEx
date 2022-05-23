
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% 
//자바 부분 - 데이터 처리 할 시 사용
//넘어오는 데이터 받기
String noStr = request.getParameter("no");
long no = Long.parseLong(noStr);
%>
<!--  처리 된 데이터 표시할 때 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

</head>
<body>
<h1>게시판 글 보기</h1>
글 번호 : <%=no %><br>

<a href = "updateForm.jsp?no=<%=no%>"><button>수정</button></a>
<a href = "delete.jsp?no=<%=no%>"><button>삭제</button></a>
<a href = "list.jsp?no=<%=no%>"><button>리스트</button></a>
</body>
</html>
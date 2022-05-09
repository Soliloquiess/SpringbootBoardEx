<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집
String noStr = request.getParameter("no");
long no = Long.parseLong(noStr);

// 데이터를 DB에 전달하면서 no에 맞는 DB 데이터를 가져 오기
// 여러개의 데이터를 한개로 넘길때 -> 1. 클래스 작성하는 방법. 2. Object 배열을 만든다.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

글번호 : <%=no %>

<a href = "updateForm.jsp?no=<%=no %>"><button>수정</button></a>
<a href = "delete.jsp?no=<%=no %>"><button>삭제</button></a>
<a href = "list.jsp"><button>리스트</button></a>

</div>
</body>
</html> 
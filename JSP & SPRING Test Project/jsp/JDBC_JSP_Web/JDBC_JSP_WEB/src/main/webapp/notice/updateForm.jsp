<%@page import="com.notice.vo.NoticeVO"%>
<%@page import="com.notice.service.NoticeViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// DB에서 데이터를 꺼내온다. 글번호가 넘어온다.
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

System.out.println("updateForm.jsp - no : " + no);

//데이터 불러오기 [Controller - jsp] - service - dao
NoticeViewService service = new NoticeViewService();
NoticeVO vo = service.service(no);

System.out.println("updateForm.jsp - vo : " + vo);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 수정 폼</title>
</head>
<body>
<h2>공지 수정 폼</h2>
<form action="update.jsp" method="post">
<table>
	<tr>
		<th>번호</th>
		<td>
			<input name="no" readonly="readonly" value="<%= vo.getNo() %>" 
			 style="background: #eee">
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<!-- pattern : 정규표현식 - 입력한 데이터가 정규표현식과 맞는지 검사
					. - 모든문자, {최소입력 글자수, 최대입력 글자수}
				 required : 필수 입력 항목을 지정 -> patter에서는 데이터가 들어와야 검사하기 때문에 같이 쓰인다.
				 title : 마우스가 올라 갔을 때 버블 창에 나타나는 텍스트. pattern이 틀리면 나타는 문자로도 사용됨.
			 -->
			<input name="title" placeholder="제목입력 - 4자이상 한글로 100자 이내" 
			maxlength="100" pattern=".{4,100}" required="required"
			title="제목입력 - 4자이상 한글로 100자 이내" value="<%= vo.getTitle() %>">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="7" cols="80" placeholder="공지 내용 입력 2000자 이내" required="required"
			 name="content"><%= vo.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th>시작일</th>
		<td>
			<!-- 정규 표현식 - \d : 숫자. [0-9] -->
			<input name="startDate" placeholder="yyyy-mm-dd" 
			 maxlength="10" pattern="\d{4}-\d{2}-\d{2}" title="날짜형식 : yyyy-mm-dd"
			 value="<%= vo.getStartDate() %>" >
		</td>
	</tr>
	<tr>
		<th>종료일</th>
		<td>
			<input name="endDate" placeholder="yyyy-mm-dd" 
			 maxlength="10"pattern="\d{4}-\d{2}-\d{2}" title="날짜형식 : yyyy-mm-dd"
			 value="<%= vo.getEndDate() %>" >
		</td>
	</tr>
	<tr>
		<td colspan="2" >
			<button>수정</button>
			<button type="reset">새로고침</button>
			<button type="button" onclick="history.back()">취소</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="com.webjjang.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분입니다.
// 데이터 받기 - 글번호 --> 글보기 : view.jsp
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
//DB 글번호에 맞는 데이터 가져오기 - BoardViewService 생성 - 호출
BoardViewService service = new BoardViewService();
BoardVO vo = service.service(no,0);
System.out.println("updateForm.jsp - vo : " + vo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
</head>
<body>
<h2>게시판 글수정</h2>
<!-- 사용자에게 데이터를 입력하도록 한다. : form, input, select, textarea tag -->
<!-- action : 데이터를 받을 URL, method : get - URL 뒤에 데이터, post - 따로 보이지 않게 데이터 -->
<form action="update.jsp" method="post">
	<table>
		<tr>
			<th>번호</th>
			<!-- input 데이터 입력, value : 입력란의 초기값 -->
			<td><input type="text" name="no" value="<%= vo.getNo() %>" 
				readonly="readonly"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="title" maxlength="100"
			 value="<%= vo.getTitle() %>" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="7" cols="80" name="content"><%= vo.getContent() %></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<td><input type="text" name="writer" maxlength="10" value="<%= vo.getWriter() %>"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
				<button type="submit">수정</button>
				<button type="reset">다시 입력</button>
				<button type="button" onclick="history.back()">취소</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
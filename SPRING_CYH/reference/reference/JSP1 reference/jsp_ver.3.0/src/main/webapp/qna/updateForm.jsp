<%@page import="com.webjjang.qna.vo.QnaVO"%>
<%@page import="com.webjjang.qna.service.QnaViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분입니다.
// 데이터 받기 - 글번호 --> 글보기 : view.jsp
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
//DB 글번호에 맞는 데이터 가져오기 - QnaViewService 생성 - 호출
QnaViewService service = new QnaViewService();
QnaVO vo = service.service(no,0);
System.out.println("updateForm.jsp - vo : " + vo);

// EL이나 JSTL에세 데이터를 사용하기 위해서 기본 저장 객체에 저장해 둔다.
request.setAttribute("vo", vo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문답변 수정</title>
</head>
<body>
<div class="container">
<h2>질문답변 수정</h2>
<!-- 사용자에게 데이터를 입력하도록 한다. : form, input, select, textarea tag -->
<!-- action : 데이터를 받을 URL, method : get - URL 뒤에 데이터, post - 따로 보이지 않게 데이터 -->
<form action="update.jsp" method="post"  class="form-horizontal">
		<div class="form-group">
			<label  for="no" class="control-label col-sm-2">번호</label>
			<!-- input 데이터 입력, value : 입력란의 초기값 -->
			<div class="col-sm-10">
				<input type="text" name="no" value="${vo.no }" id="no"
				readonly="readonly" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label  for="title" class="control-label col-sm-2">제목</label>
			<!-- input 데이터 입력, type : 입력형태, name : 전달 이름, maxlength : 최대 입력 -->
			<div class="col-sm-10">
				<input type="text" name="title" maxlength="100" id="title"
				 value="${vo.title }" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label  for="conent" class="control-label col-sm-2">내용</label>
			<div class="col-sm-10">
				<textarea rows="7" name="content" class="form-control" id="content"
				>${vo.content }</textarea>
			</div>
		</div>
		<div class="text-center">
			<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
			<button type="submit" class="btn btn-default">수정</button>
			<button type="reset" class="btn btn-default">다시 입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
</form>
</div>
</body>
</html>
<%@page import="com.board.service.BoardUpdateService"%>
<%@page import="com.board.service.BoardWriteService"%>
<%@page import="com.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.board.service.BoardListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
//데이터 가져오기 - 생성하고 호출한다.
//DB 클래스 확인
Class.forName("com.util.db.DB");
//한글처리
request.setCharacterEncoding("utf-8");
//여기가 자바의처리 부분
//[Controller(jsp)]] - service - DAO

//넘어오는 데이터 받기
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);


String title= request.getParameter("title");
String content= request.getParameter("content");
String writer= request.getParameter("writer");

//[Controller(jsp)]] - service - DAO:BoardVO객체를 만들어서 전달한다
BoardVO vo = new BoardVO();
vo.setNo(no);
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

System.out.println("게시판 글 수정-"+vo);
//BoardUpdateService->BoardDAO.update


BoardUpdateService service = new BoardUpdateService();
service.service(vo);


//게시판 리스트로 자동 이동시킨다.
// response.sendRedirect("list.jsp"); 
response.sendRedirect("view.jsp?no="+no+"&inc=0"); 
%>
<!DOCTYPE html>
<html>
<!--  페이지 정보 -->

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td {
	border: 1px solid;
	padding: 5px;
	align-center;
}
</style>
<script type="java/javascript">

</script>
</head>
<!--  데이터 표시 부분  -->
<body>
	게시글 등록이 완료되었음.
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
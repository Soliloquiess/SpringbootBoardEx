<%@page import="com.webjjang.image.service.ImageUpdateService"%>
<%@page import="com.webjjang.image.service.ImageWriteService"%>
<%@page import="com.webjjang.image.vo.ImageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
/// 여기는 자바 입니다.
// 한글 처리
request.setCharacterEncoding("utf-8");
// 넘어오는 데이터 받기
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
String title = request.getParameter("title");
String content = request.getParameter("content");

// Controller -> Service -> DAO : ImageVO 객체를 만들어서 전달한다.
ImageVO vo = new ImageVO();
vo.setNo(no);
vo.setTitle(title);
vo.setContent(content);

System.out.println("이미지 정보 수정 - " + vo);
// ImageUpdateService -> ImageDAO.update()
ImageUpdateService service = new ImageUpdateService();
int result = service.service(vo);

// 게시판 리스트로 자동 이동시킨다.
response.sendRedirect("view.jsp?no=" + no);
%>

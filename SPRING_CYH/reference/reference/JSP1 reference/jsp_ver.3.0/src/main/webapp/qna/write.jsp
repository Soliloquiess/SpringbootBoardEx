<%@page import="com.webjjang.qna.service.QnaWriteService"%>
<%@page import="com.webjjang.qna.vo.QnaVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.board.service.BoardWriteService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/// 여기는 자바 입니다.
// 데이터 수집
// 넘어오는 데이터 받기
String title = request.getParameter("title");
String content = request.getParameter("content");
// 아이디는 session에서 꺼낸다.
String id = ((LoginVO)session.getAttribute("login")).getId();

// Controller -> Service -> DAO : QnaVO 객체를 만들어서 전달한다.
QnaVO vo = new QnaVO();
vo.setTitle(title);
vo.setContent(content);
vo.setId(id);

System.out.println("질문 등록 - " + vo);

// DB에 등록 처리
QnaWriteService service = new QnaWriteService();
service.service(vo);

// 질문 답변 리스트로 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>

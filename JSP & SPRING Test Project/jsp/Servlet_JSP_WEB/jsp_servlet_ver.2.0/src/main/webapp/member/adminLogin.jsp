<%@page import="com.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 스크립트릿
// 강제 로그인 시킨다.
LoginVO vo = new LoginVO();
vo.setId("admin");
vo.setName("이영환");
vo.setGradeNo(9);
vo.setGradeName("관리자");

session.setAttribute("login", vo);

//자동으로 게시판 리스트로 이동시킨다.
response.sendRedirect("/board/list.do");

%>
<%= "표현식" %>
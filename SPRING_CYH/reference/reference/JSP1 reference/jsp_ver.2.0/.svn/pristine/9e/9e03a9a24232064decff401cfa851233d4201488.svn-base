<%@page import="com.webjjang.notice.service.NoticeWriteService"%>
<%@page import="com.webjjang.notice.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 처리
request.setCharacterEncoding("utf-8");
// 데이터 수집 - 제목, 내용, 시작일, 종료일
String title = request.getParameter("title");
String content = request.getParameter("content");
String startDate = request.getParameter("startDate");
String endDate = request.getParameter("endDate");

NoticeVO vo = new NoticeVO();
vo.setTitle(title);
vo.setContent(content);
vo.setStartDate(startDate);
vo.setEndDate(endDate);

System.out.println("write.jsp - vo : " + vo);

// DB에 저장한다. Controller(JSP) - service - dao
NoticeWriteService service = new NoticeWriteService();
int result = service.service(vo);

// list.jsp 로 자동 이동 시킨다.
response.sendRedirect("list.jsp");
%>

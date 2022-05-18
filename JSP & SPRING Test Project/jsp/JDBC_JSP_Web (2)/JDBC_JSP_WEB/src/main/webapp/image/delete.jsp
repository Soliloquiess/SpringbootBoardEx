<%@page import="java.io.File"%>
<%@page import="com.image.service.ImageDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
String oldFileName = request.getParameter("oldImage");

System.out.println("delete.jsp - no : " + no + ", oldImage : " + oldFileName);

// DB 에서 삭제하기 - 첨부된 파일은 따로 삭제 처리해야만 한다.
ImageDeleteService service = new ImageDeleteService();
int result = service.service(no);

// DB에서 데이터가 삭제되었으면 파일도 삭제한다.
//파일명으로 realPath를 구한다. -> File 객체로 만든다. delete() 호출해서 삭제한다.(파일 객체는 자바에서 제공함)
File oldFile = new File(request.getServletContext().getRealPath(oldFileName));

if(result > 0 && oldFile.exists())
	oldFile.delete();
	
// 리스트로 자동 이동
response.sendRedirect("list.jsp");
%>


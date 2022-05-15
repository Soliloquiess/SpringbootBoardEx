<%@page import="com.image.service.ImageWriteService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//위에 oreilly,나 다른 호환문제는 cos.jar받아서 web-inf에 넣어주면 바로 임포트 가능.
// 서버에서 저장할 localhost 뒤에 붙는 위치가 된다.
String path = "/upload/image/";
String realPath = request.getServletContext().getRealPath(path);
System.out.println("write.jsp - realPath : " + realPath);
// 저장 용량 제한
int size = 10 * 1024 * 1024; // 10M

// 실제적으로 파일 업로드 하는 처리문
// new MultipartRequest(request, 저장위치, 용량제한, 엔코딩, 중복파일명 처리 객체)
MultipartRequest multi 
= new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());

// 데이터 수집 - multi - 파일이름, 텍스트 데이터, session - 아이디
String title = multi.getParameter("title");
String content = multi.getParameter("content");
String fileName = multi.getFilesystemName("image");
LoginVO loginVO = (LoginVO) session.getAttribute("login");
String id = loginVO.getId();

ImageVO vo = new ImageVO();
vo.setTitle(title);
vo.setContent(content);
vo.setId(id);
vo.setFileName(path + fileName);

System.out.println("write.jsp - vo : " + vo);

//DB 이미지 등록 - Controller(JSP) - Service - dao : MVC Model I
ImageWriteService service = new ImageWriteService();
int result = service.service(vo);

//service.service(vo) 이렇게 줘도 실행은 됨. result는 나중에 쓸 일이 있을 지 모르니 매개변수에 담아둔 것.

// 자동으로 리스트로 이동한다.
response.sendRedirect("list.jsp");
%>

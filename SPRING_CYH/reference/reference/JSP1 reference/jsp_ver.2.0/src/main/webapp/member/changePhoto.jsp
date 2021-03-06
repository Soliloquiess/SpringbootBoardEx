<%@page import="java.io.File"%>
<%@page import="com.webjjang.member.service.ChangePhotoService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//서버에서 저장할 localhost 뒤에 붙는 위치가 된다.
String path = "/upload/member/";
String realPath = request.getServletContext().getRealPath(path);
System.out.println("changePhoto.jsp - realPath : " + realPath);
//저장 용량 제한
int size = 10 * 1024 * 1024; // 10M

//실제적으로 파일 업로드 하는 처리문
//new MultipartRequest(request, 저장위치, 용량제한, 엔코딩, 중복파일명 처리 객체)
MultipartRequest multi 
= new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());

//데이터 수집 - multi - 바꾼 사진의 이름, 아이디
String id = multi.getParameter("id");
String photo = multi.getFilesystemName("photo");
// DB 변경이 끝나면 이전 사진 삭제할 파일이름
String oldPhoto = multi.getParameter("oldPhoto");
System.out.println("changePhoto.jsp - oldPhoto : " + oldPhoto);

// DB 처리를 위한 VO객체 만들기
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPhoto(path + photo);

System.out.println("changePhoto.jsp - vo : " + vo);

//DB 처리 - Controller(JSP) - Service - dao
ChangePhotoService service = new ChangePhotoService();
int result = service.service(vo);


//result 가 0 보다 크면 변경이 되었으므로 이전 파일은 삭제한다.
//이전 파일명으로 realPath를 구한다. -> File 객체로 만든다. delete() 호출해서 삭제한다.
File oldFile = new File(request.getServletContext().getRealPath(oldPhoto));
//oldFile.exists() -> 파일이나 폴더가 존재한다.(true)
if(result > 0 && oldFile.exists() && !oldPhoto.equals("/upload/member/noImage.jpg"))
	oldFile.delete();

response.sendRedirect("view.jsp?id=" + vo.getId());
%>
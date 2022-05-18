<%@page import="java.io.File"%>
<%@page import="com.image.service.ImageChangeService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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

// 데이터 수집 - multi - 파일이름, 텍스트 데이터(번호)
String strNo = multi.getParameter("no");
long no = Long.parseLong(strNo);	//Long은 Long타입으로 저장할 수 있는 클래스 변수라는 뜻.그걸 parseLong해주는 것.
String fileName = multi.getFilesystemName("image");
// DB 변경이 다 되면 삭제할 파일
String oldFileName = multi.getParameter("oldImage");

// 실행 쿼리 - update image set fileName = ? where no = ?
ImageVO vo = new ImageVO();
vo.setNo(no);
vo.setFileName(path + fileName);

System.out.println("changeImage.jsp - vo : " + vo);

//DB 이미지 변경 - Controller(JSP) - Service - dao : MVC Model I
ImageChangeService service = new ImageChangeService();
int result = service.service(vo);

// result 가 0 보다 크면 변경이 되었으므로 이전 파일은 삭제한다.
// 이전 파일명으로 realPath를 구한다. -> File 객체로 만든다. delete() 호출해서 삭제한다.
File oldFile = new File(request.getServletContext().getRealPath(oldFileName));
// oldFile.exists() -> 파일이나 폴더가 존재한다.(true)
if(result > 0 && oldFile.exists())
	oldFile.delete();

// 자동으로 이미지 보기로 이동한다. 글번호를 전달
response.sendRedirect("view.jsp?no=" + vo.getNo());
%>

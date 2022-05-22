<%@page import="com.util.PageObject"%>
<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.image.service.ImageListService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- 페이지 네비게이션 쓰게 만들어주고, 태그라이브러리 작업환경 만듬 -->
<%
// 데이터 수집 - 없다.

// 페이지 정보 받기
PageObject pageObject = PageObject.getInstance(request);
//대문자로 받는 이유는 PageObject가 스태틱이기 때문


// DB에서 데이터를 가져온다. - controller(JSP) - ImageListService - ImageDAO
ImageListService service = new ImageListService();
//이 페이지 오브젝트를 서비스에 집어 넣으면 페이지에 맞는 정보가 나온다

List<ImageVO> list = service.service(pageObject);

System.out.println("list.jsp - list : " + list);
// System.out.println("list.jsp - 10/0 : " + (10/0));

// 로그인 한 경우만 이미지 등록이 가능하도록 한다. - 로그인한 정보는 session에 있다.
LoginVO loginVO = (LoginVO) session.getAttribute("login");

// EL객체나 JSTL을 사용하기 위해서 서버의 기본 저장 객체에 담는다.
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트</title>
<style type="text/css">
th, td{
	border: 1px solid #	44;
	padding: 5px
}
.dataRow:hover{
	cursor: pointer;
	background: #eee;
}
/* dataRow class(.) 안에 있는 이미지 태그의 사이즈 조정 */
.dataRow img{
	width: 40px;
	height: 45px
}
</style>
</head>
<body>
<div class="container">
<h2>이미지 리스트</h2>

<div class="row">
	<!-- col-md-3 : 한 줄에 사진 4장 표시 3 * 4 = 12 -->
	
	<c:forEach items="${list }" var="vo" varStatus="vs">
	  <div class="col-md-3">
	    <div class="thumbnail dataRow" onclick="location='view.jsp?no=${vo.no}'">
	        <img src="${vo.fileName }" alt="Photo Lists" style="width:100%;height: 300px;">
	        <div class="caption">
	          <p>[${vo.no }] ${vo.title }</p>
	          ${vo.name }(${vo.id }) - ${vo.writeDate }
	        </div>
	    </div>
	  </div>
	  	<c:if test="${vs.count % 4 == 0 && vs.count != list.size()}">
	  	<!-- 이건 get이 아니라 list에 있는 size메서드 호출하는 거. -->
	  	
<%-- 	  	<%
/* 	  	i=0; */
	  	i++;//이러면 1부터 시작
	  	//이미지 4개를 출력하려면 새로운 줄을 만든다 만약 출력된 이미지가 총 데이터 개수와 같으면 더 이상 만들지 않는다.
	  	if(i%4==0 && i!= list.size()){
	  	%> --%>
			<!-- 한 줄을 마감하고 새로운 줄을 시작한다. -->
			${"</div>" } 
			${"<div class='row'>"}
		</c:if>
	</c:forEach>
</div>
<div>
	<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }" />
</div>
<c:if test="${!empty login }">
	<a href="writeForm.jsp" class="btn btn-default">등록</a>
</c:if>
<a href="list.jsp" class="btn btn-default">새로고침</a>
</div>
</body>
</html>
<%@page import="com.member.vo.LoginVO"%>
<%@page import="com.image.service.ImageListService"%>
<%@page import="com.image.vo.ImageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터 수집 - 없다.
// DB에서 데이터를 가져온다. - controller(JSP) - ImageListService - ImageDAO
ImageListService service = new ImageListService();
List<ImageVO> list = service.service();

System.out.println("list.jsp - list : " + list);
// System.out.println("list.jsp - 10/0 : " + (10/0));

// 로그인 한 경우만 이미지 등록이 가능하도록 한다. - 로그인한 정보는 session에 있다.
LoginVO loginVO = (LoginVO) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트</title>
<style type="text/css">
th, td{
	border: 1px solid #444;
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
	
	<%
	  int i = 0;
	  for(ImageVO vo : list) { %>
	  <div class="col-md-3">
	    <div class="thumbnail dataRow" onclick="location='view.jsp?no=<%= vo.getNo() %>'">
	        <img src="<%= vo.getFileName() %>" alt="Photo Lists" style="width:100%;height: 300px;">
	        <div class="caption">
	          <p>[<%= vo.getNo() %>] <%= vo.getTitle() %></p>
	          <%= vo.getName() %>(<%= vo.getId() %>) - <%= vo.getWriteDate() %>
	        </div>
	    </div>
	  </div>
	<%
	   i ++;
		// 이미지 4개를 출력하면 새로운 줄을 만든다. 만약에 출력된 이미지가 총 데이터의 갯수와 같으면 더이상 만들지 않는다.
		if(i % 4 == 0 && i != list.size() ){ %>
		<!-- 한 줄을 마감하고 새로운 줄을 시작한다. -->
		</div>
		<div class="row">
	<%	
		} // if의 끝
	  } // for의 끝 %>
</div>

<% if(loginVO != null) { %>
	<a href="writeForm.jsp" class="btn btn-default">등록</a>
<% } %>
<a href="list.jsp" class="btn btn-default">새로고침</a>
</div>
</body>
</html>
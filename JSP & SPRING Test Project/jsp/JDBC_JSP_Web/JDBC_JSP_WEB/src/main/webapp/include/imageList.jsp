<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4>
				이미지
			</h4>
		</div>
		<div class="panel-body">
			<div class="row">
				<!-- col-md-4 : 한 줄에 사진 3장 표시 4 * 3 = 12 -->
				<c:forEach items="${imageList }" var="vo">
				  <div class="col-md-4">
				  
				  <%-- 
				    <div class="thumbnail imageDataRow" onclick = "location='view.jsp?no=${vo.no}'"> --%>
				    <div class="thumbnail imageDataRow" data-no="${vo.no}">
				        <img src="${vo.fileName }" alt="Photo Lists">
				        <div class="caption">
				          <p>[${vo.no }] ${vo.title }</p>
				          ${vo.name }(${vo.id }) - ${vo.writeDate }
				        </div>
				    </div>
				  </div>
				</c:forEach>
			</div>
		</div>
	</div>

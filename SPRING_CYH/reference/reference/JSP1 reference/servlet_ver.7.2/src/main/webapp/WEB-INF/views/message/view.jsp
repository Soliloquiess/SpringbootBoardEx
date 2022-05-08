<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>메시지 보기</h1>
<ul class="list-group">
  <li class="list-group-item">
  	<label>번호 : </label> <span>${vo.no }</span>
  </li>
  <li class="list-group-item">
  	<label>보낸 분 : </label> <span>${vo.senderName}(${vo.sender })</span>
  </li>
  <li class="list-group-item">
  	<label>보낸 날짜 : </label> <span>${vo.sendDate }</span>
  </li>
  <li class="list-group-item">
  	<label>받는 분 : </label> <span>${vo.accepterName}(${vo.accepter })</span>
  </li>
  <li class="list-group-item">
  	<label>받은 날짜 : </label> <span>${vo.acceptDate }</span>
  </li>
  <li class="list-group-item">
  	<label>내용 : </label> <span>${vo.content }</span>
  </li>
</ul>
</div>
</body>
</html>
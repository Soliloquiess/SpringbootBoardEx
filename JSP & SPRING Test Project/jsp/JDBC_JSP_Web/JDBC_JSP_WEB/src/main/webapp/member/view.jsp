<%@page import="com.member.vo.MemberVO"%>
<%@page import="com.member.service.MemberViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//데이터 수집 - 아이디
Class.forName("com.util.db.DB");
//String strNo = request.getParameter("no");
String id = request.getParameter("id");
//long no = Long.parseLong(strNo);
//String strInc = request.getParameter("inc");
//long inc = Long.parseLong(strInc);
//Controller(JSP)- BoardViewService-BoardDAO
//BoardViewService 클래스 작성 생성 후 호출
//BoardDAO 클래스에 view (long no) 추가 작성

// inc:조회수 증가 여부 1:증가, 0 :미증가
MemberViewService service = new MemberViewService();
MemberVO vo = service.service(id);
//System.out.println("view.jsp - vo : " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>

<!-- jquery 라이브러리 등록. jquery()==$() JS를 쉽게 처리 하기 위한 함수가 들어있다.jquery() == $-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<style type="text/css">
th, td {
	border: 1px solid;
	padding: 5px;
	/*padding = 안에 여백 : 데이터 한개 - 사방을 적용 데이터 2개 - 위아래, 데이터 4개 - 위 우 아래 좌*/
	
}

/*id가 deleteDiv안에 label태그를 선택해서 css를 적용ㅣ켜라*/
#deleteDiv label {
	margin : 0 5px 0 10px;
}


#deleteDiv {
	border:1px solid #888;
	margin:10px 0
	padding:10px;
	width:600px;
}

#id, #pw, #tel {
	width: 100px;
}
</style>

<script type="text/javascript">
	//body(문서-document)가  다 로딩이 되면 동작이 되도록 선언한 형태

	$(function() {
		//deleteDiv id를 찾아서 슘긴다(hide)
		//deleteDiv가 보였다가 숨겨진다. -> 원래부터 아노이기 css의 visiability 속성을 이용한다.ㄴ
		$("#deleteDiv").hide();
		//.onload="alert('test'); $(this).hide();";
	});
</script>


</head>
<body>

	<h2>회원 정보 글 보기</h2>
	<table>
		<tr>
			<th>아이디</th>
			<td><%=vo.getId()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=vo.getName()%></td>
		</tr>

		<tr>
			<th>성별</th>
			<td><%=vo.getGender()%></td>
		</tr>

		<tr>
			<th>생년월일</th>
			<td><%=vo.getBirth()%></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><%=vo.getTel()%></td>
		</tr>
		<tr>
			<th>email</th>
			<td><%=vo.getEmail()%></td>
		</tr>
		<tr>
			<th>회원 등록일</th>
			<td><%=vo.getRegDate()%></td>
		</tr>
		<tr>
			<th>최근 접속일</th>
			<td><%=vo.getConDate()%></td>
		</tr>
		<tr>
			<th>회원상태</th>
			<td><%=vo.getStatus()%></td>
		</tr>

		<tr>
			<th>등급</th>
			<td><%=vo.getGradeName()%>(<%=vo.getGradeNo()%>)</td>
		</tr>

	</table>


	<a href="updateForm.jsp?id=<%=vo.getId()%>"><button>수정</button></a>
	<%-- <a href="delete.jsp?id=<%=vo.getId()%>"><button>삭제</button></a> --%>
	<button onclick="$('#deleteDiv').show();">삭제</button>

	<a href="list.jsp"><button>리스트</button></a>
	<div id="deleteDiv">
		<h3>삭제에 필요한 정보 입력</h3>
		<form action="delete.jsp" method="post">
			<label for="id">아이디</label><input name="id" id="id"> <label
				for="pw">비밀번호</label><input name="pw" id="pw"> <label
				for="tel">전화번호</label><input name="tel" id="tel">
			<button>삭제</button>
			<button type="button" onclick="$('#deleteDiv').hide();">취소</button>
		</form>
	</div>
</body>
</html>
<%@page import="com.board.vo.BoardVO"%>
<%@page import="com.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
//Controller(JSP)- BoardViewService-BoardDAO
//BoardViewService Ŭ���� �ۼ� ���� �� ȣ��
//BoardDAO Ŭ������ view (long no) �߰� �ۼ�
BoardViewService service = new BoardViewService();
BoardVO vo = service.service(no);
System.out.println("view.jsp - vo : " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type = "text/css">
th,td{
	border : 1px solid;
	padding : 5px;
	align-center;
}

</style>
</head>
<body>
	���� ���� ��ȣ
	<%=no%>
	<h2>�Խ��� �� ����</h2>
	<table>
		<tr>
			<th>��ȣ</th>
			<td><%=vo.getNo()%></td>
		</tr>
		<tr>
			<th>����</th>
			<td><%=vo.getContent()%></td>
		</tr>

		<tr>
			<th>�ۼ���</th>
			<td><%=vo.getWriter()%></td>
		</tr>

		<tr>
			<th>�ۼ���</th>
			<td><%=vo.getWriteDate()%></td>
		</tr>
		<tr>
			<th>��ȸ��</th>
			<td><%=vo.getHit()%></td>
		</tr>

	</table>
	<a href = "updateForm.jsp?no=<%=vo.getNo()%>"><button>����</button></a>
	<a href = "delete.jsp?no=<%=vo.getNo()%>"><button>����</button></a>	
	<a href = "list.jsp"><button>����Ʈ</button></a>
</body>
</html>
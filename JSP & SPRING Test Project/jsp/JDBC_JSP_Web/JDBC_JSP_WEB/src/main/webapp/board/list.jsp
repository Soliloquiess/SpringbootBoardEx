<%@page import="com.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.board.service.BoardListService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
//������ �������� - �����ϰ� ȣ���Ѵ�.
//DB Ŭ���� Ȯ��
Class.forName("com.util.db.DB");
//���Ⱑ �ڹ���ó�� �κ�
//[Controller(jsp)]] - service - DAO
BoardListService boardListService = new BoardListService();
List<BoardVO> list = boardListService.service();
System.out.println(list);
%>
<!DOCTYPE html>
<html>
<!--  ������ ���� -->

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
th, td {
	border: 1px solid;
	padding: 5px;
	align-center;
}
</style>
<script type="java/javascript">

</script>
</head>
<!--  ������ ǥ�� �κ�  -->
<body>
	<h2>�Խ��� ����Ʈ</h2>
	<table>
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�ۼ���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
		</tr>
		<!-- ������ �ִ� ��ŭ �ݺ�ó���ؼ� ���� ������ -->
		<%
		for (BoardVO vo : list) {
		%>
		<tr>
			<td><%=vo.getNo()%></td>
			<td><a href="view.jsp?no=<%=vo.getNo()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=vo.getWriteDate()%></td>
			<td><%=vo.getHit()%></td>
		</tr>
		<%
		} //for���� ��
		%>
		<tr>
			<td colspan="5">
				<a href = "writeForm.jsp"><button>�۾���</button></a>
			</td>
		</tr>
	</table>
</body>
</html>
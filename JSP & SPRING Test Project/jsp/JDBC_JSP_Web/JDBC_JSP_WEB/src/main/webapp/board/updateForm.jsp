<%@page import="com.board.vo.BoardVO"%>
<%@page import="com.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
//�ڹ� �κ��Դϴ�.
//������ �ޱ� - �۹�ȣ ->�� ���� :view.jsp
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
//DB �� ��ȣ�� �´� ������ �����´�. - BoardViewService ���� - ȣ��
BoardViewService service = new BoardViewService();
BoardVO vo = service.service(no);
System.out.println("updateForm.jsp - vo " + vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� �ۼ��� ��</title>
</head>
<body>
	<h2>�Խ��� �� ����</h2>


	<form action="update.jsp" method="post">
		<table>
			<tr>
				<th>��ȣ</th>
				<!--  input ������ �Է�, type = �Է����� name:���� �̸� maxlength: �ִ��Է� -->

				<td><input type="text" name="no" value = "<%=vo.getNo() %>" 
				readonly="readonly"/></td>
			</tr>
			<tr>
				<th>����</th>
				<!--  input ������ �Է�, type = �Է����� -->

				<td><input type="text" name="title" maxlength="100" value = "<%=vo.getTitle() %>"/></td>
			</tr>
			<tr>
				<th>����</th>
				<td><textarea rows="7" cols="80" name="content"><%=vo.getContent()%></textarea></td>
			</tr>

			<tr>
				<th>�ۼ���</th>
				<td><input type="text" name="writer" maxlength="10" value ="<%=vo.getWriter()%>"> </textarea></td>
			</tr>


			<tr>
				<td colspan="2">
					<!--  ��ư�� form �±� �ȿ� ������ �����͸� �����ϴ� ������ �ϰ�  �ȴ�.	 -->
					<button type="submit">����</button>
					<button type="reset">�ٽ� �Է�</button>
					<button type="button" onclick="history.back()">���</button>

				</td>
			</tr>


		</table>
	</form>

</body>
</html>
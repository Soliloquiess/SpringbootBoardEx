<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� �۾��� ��</title>
</head>
<body>
	<h2>�� ���� ��</h2>
	<!-- ����ڿ��� �����͸� �Է��ϵ��� �Ѵ�. -->
	<!-- action: �����͸� ���� URL, method : get-URL �ڿ� ������, post: �ڿ� ���� ������ �ʰ� ������ ���� -->
	<form action="write.jsp" method = "post">
		<table>
			<tr>
				<th>����</th>
				<!--  input ������ �Է�, type = �Է����� -->

				<td><input type="text" name="title" maxlength="100" /></td>
			</tr>
			<tr>
				<th>����</th>
				<td><textarea rows="7" cols="80" name="content"></textarea></td>
			</tr>

			<tr>
				<th>�ۼ���</th>
				<td><input type="text" name="writer" maxlength="10">
				</textarea></td>
			</tr>


			<tr>
				<td colspan="2">
				<!--  ��ư�� form �±� �ȿ� ������ �����͸� �����ϴ� ������ �ϰ�  �ȴ�.	 -->
					<button type="submit">���</button>
					<button type="reset">�ٽ� �Է�</button>
					<button type ="button" onclick = "history.back()">���</button>
					
				</td>
			</tr>


		</table>
	</form>
</body>
</html>
package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.util.db.DB;

// insert into board(no, title, content, writer) values(board_seq.nextval, 'java', 'java jjang','lee')
// �����ϱ� ���� ���α׷�
public class BoardWrite {

	public static void main(String[] args) throws Exception {
		
//		Class.forName("board.DB");

		Class.forName("com.util.db.DB");
		
		// DB�� �Է��� ���� - ����� �Է�
		String title = "JDBC";
		String content = "�����ͺ��̽� ���� ���α׷�";
		String writer = "yacho";
		
		// DB ����

		
		// ��밴ü
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// ����ó��
		try {
			// 1. ����̹�Ȯ��
			
//			System.out.println("1. ����̹� Ȯ�� �Ϸ�");
			
			// 2. ����
//			con = DriverManager.getConnection(url, UID, UPW);
			
			con = DB.getConnection();
			System.out.println("2. ���� �Ϸ� - " + con);
			
			// 3. ���� SQL �ۼ�
			String sql = "insert into board(no, title, content, writer) "
					+ " values(board_seq.nextval, ?, ?, ?)";
			System.out.println("3. SQL - " + sql);
			
			// 4. ���ఴü & ������ ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			System.out.println("4. ���ఴü ���� - " + pstmt);
			
			// 5. ���� - select : executeQuery() / insert, update, delete : executeUpdate()
			// result �� 1�̸� ���������� ��ϵ�. 0 �̸� ��� �ȵ�. insert�� ���� 0�� ������ �ʴ´�. 0�̶�� �� ����
			int result = pstmt.executeUpdate();
			System.out.println("5. ���� �Ϸ�. result - " + result);
			
			// 6. ǥ�� �Ǵ� ����
			System.out.println("6. �Խ��� ��� �Ϸ�.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. ��밴ü �ݱ�
//				if(con != null) con.close();
//				if(pstmt != null) pstmt.close();
				
				DB.close(con, pstmt);
				System.out.println("7. ��ü �ݱ� ����");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
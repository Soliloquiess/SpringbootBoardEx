package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.db.DB;

public class BoardList {

	public static void main(String[] args) throws Exception {
		// DB ���� ����

//		Class.forName("board.DB");

		Class.forName("com.util.db.DB");
		// ����� ��ü
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. ����̹��� �ִ��� Ȯ���ϰ� Ŭ������ ������ �ε�(static)�ؾ߸� �Ѵ�. 
			// -> ������ ���ư��� ��ó�� �ѹ��� �ϸ�ȴ�.
//			Class.forName(driver);
			System.out.println("1. ����̹�Ȯ�� �Ϸ�.");
			
			// 2. ���� ���� - ����, ����� ����
//			con = DriverManager.getConnection(url, UID, UPW);

			con = DB.getConnection();
			System.out.println("2. ����Ŭ ���� ���� �Ϸ�. - " + con);
			
			//3. ������ SQL�� �ۼ�
			String sql = "select no, title, writer, writeDate, hit "
					+ " from board order by no desc";
			System.out.println("3. ������ SQL ���� - " + sql);
			
			//4. �ۼ��� ������ �����ϱ� ���� ��ü & ������ ����
			pstmt = con.prepareStatement(sql);
			System.out.println("4. ���� ��ü ���� �Ϸ� : " + pstmt);
			
			// 5. ���� - select : executeQuery() / insert, update, delete : executeUpdate()
			rs = pstmt.executeQuery();
			System.out.println("5. ���� �Ϸ� : " + rs);
			
			// 6. ǥ�� �Ǵ� ������ ���
			if(rs != null) {
				System.out.println("===============================================");
				System.out.println("��ȣ  |    ����    |  �ۼ���  |   �ۼ���  | ��ȸ��");
				System.out.println("===============================================");
				// next() - ���� �����Ͱ� ������ true�� ������ �ش�. �۾� �������� �����͸� ���� �����ͷ� �̵������ش�.
				while(rs.next()) {
					// rs.get������Ÿ��("select�� ������ �̸�")
					System.out.print(rs.getLong("no"));
					System.out.print(" | " + rs.getString("title"));
					System.out.print(" | " + rs.getString("writer"));
					System.out.print(" | " + rs.getString("writeDate"));
					System.out.print(" | " + rs.getLong("hit"));
					System.out.println();
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 7. ����� ��ü �ݱ�
//				if(con != null) con.close();
//				if(pstmt != null) pstmt.close();
//				if(rs != null) rs.close();
				

				DB.close(con, pstmt,rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
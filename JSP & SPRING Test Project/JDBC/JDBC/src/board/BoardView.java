package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.db.DB;

public class BoardView {

	public static void main(String[] args) {
		// DB ���� ����

		// ����� ��ü
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. ����̹��� �ִ��� Ȯ���ϰ� Ŭ������ ������ �ε�(static)�ؾ߸� �Ѵ�. 
			// -> ������ ���ư��� ��ó�� �ѹ��� �ϸ�ȴ�.

			Class.forName("board.DB");
			System.out.println("1. ����̹�Ȯ�� �Ϸ�.");
			
			// 2. ���� ���� - ����, ����� ����
//			con = DriverManager.getConnection(url, UID, UPW);

			con = DB.getConenction();
			System.out.println("2. ����Ŭ ���� ���� �Ϸ�. - " + con);
			
			//3. ������ SQL�� �ۼ�
			// ?�Ѱ��� ������ �Ѱ��� �ǹ��� �������� �����Ϳ� �ٲ�ġ�⸦ �Ѵ�.->������ ����
			String sql = "select no, title, content, writer, writeDate, hit "
					+ " from board where no = ?";
			System.out.println("3. ������ SQL ���� - " + sql);
			
			//4. �ۼ��� ������ �����ϱ� ���� ��ü & ������ ����
			pstmt = con.prepareStatement(sql);
			// set������Ÿ��(?�Ǽ�����ȣ, ������Ÿ���ǵ�����)
			pstmt.setLong(1, 2);
			System.out.println("4. ���� ��ü ���� �Ϸ� : " + pstmt);
			
			// 5. ���� - select : executeQuery() / insert, update, delete : executeUpdate()
			rs = pstmt.executeQuery();
			System.out.println("5. ���� �Ϸ� : " + rs);
			
			// 6. ǥ�� �Ǵ� ������ ���
			// next() - ���� �����Ͱ� ������ true�� ������ �ش�. �۾� �������� �����͸� ���� �����ͷ� �̵������ش�.
			if(rs != null && rs.next()) {
				// rs.get������Ÿ��("select�� ������ �̸�")
				System.out.println("��ȣ : " +rs.getLong("no"));
				System.out.println("���� : " + rs.getString("title"));
				System.out.println("���� : " + rs.getString("content"));
				System.out.println("�ۼ��� : " + rs.getString("writer"));
				System.out.println("�ۼ��� : " + rs.getString("writeDate"));
				System.out.println("��ȸ�� : " + rs.getLong("hit"));
			}
		} catch (Exception e) {
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

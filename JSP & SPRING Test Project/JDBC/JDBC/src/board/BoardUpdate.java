package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.util.db.DB;

// update board set title = 'oracle', content = 'oracle jjang', writer = 'son' where no = 8
public class BoardUpdate {

	public static void main(String[] args) throws Exception {
		// ������ ������ - �۹�ȣ , ����, ����, �ۼ���
		

		Class.forName("com.util.db.DB");
		
		long no = 5;
		String title = "update"; 
		String content = "updtae yacho"; 
		String writer = "yacho"; 
		
		// DB ����
		
		// ��� ��ü
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// ����ó��
		try {
			//1. Ȯ��
			// 2. ����
//			con = DriverManager.getConnection(url, UID, UPW);

			con = DB.getConnection();
			// 3. SQL
			String sql = "update board set title = ?, content = ?, writer = ? where no = ?";
			// 4. ���ఴü & ������ ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setLong(4, no);
			// 5. ����
			int result = pstmt.executeUpdate();
			// 6. ǥ�� �Ǵ� ����
			System.out.println("�Խ��� ������ �Ǿ����ϴ�. result = " + result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. �ݱ�
//				if(con != null) con.close();
//				if(pstmt != null) pstmt.close();
				

				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
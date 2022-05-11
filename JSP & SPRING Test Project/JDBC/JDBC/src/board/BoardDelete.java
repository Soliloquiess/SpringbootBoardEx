package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.util.db.DB;

// delete from board where no = 40
public class BoardDelete {

	public static void main(String[] args) throws Exception {
		// ������
		
		

//		Class.forName("board.DB");

		Class.forName("com.util.db.DB");
		long no = 2;//�� ��ȣ ���� �� ����
		
		// DB ���� - Connection�� �����

		// ��밴ü - delete : con, pstmt
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// ����ó��
		try {
			// 1. Ȯ��

			System.out.println("1. ����̹� Ȯ�� �Ϸ�");
			// 2. ����
//			con = DriverManager.getConnection(url, UID, UPW);
			

			con = DB.getConnection();
			System.out.println("2. ���� �Ϸ�");
			// 3. sql
			String sql = "delete from board where no =?";
			System.out.println("3. sql - " + sql);
			// 4. ���ఴü
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);	//�� ��ȣ ���� �� ����
			System.out.println("4. ���ఴü - " + pstmt);			
			// 5. ����
			int result = pstmt.executeUpdate();
			System.out.println("5. ���� �Ϸ� - result : " + result);
			// 6. ǥ��
			System.out.println("6. ǥ�� - �Խ��� ���� �����Ǿ����ϴ�. - " + no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. �ݱ�
//				if(con != null) con.close();
//				if(pstmt != null) pstmt.close();
				

				DB.close(con, pstmt);
				
				System.out.println("7. �ݱ� �Ϸ�");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// delete from board where no = 40
public class BoardDelete {

	public static void main(String[] args) {
		// 데이터
		long no = 2;//이 번호 가진 글 삭제
		
		// DB 정보 - Connection을 만들기
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String UID = "java00";
		String UPW = "java00";
		
		// 사용객체 - delete : con, pstmt
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 예외처리
		try {
			// 1. 확인
			Class.forName(driver);
			System.out.println("1. 드라이버 확인 완료");
			// 2. 연결
			con = DriverManager.getConnection(url, UID, UPW);
			System.out.println("2. 연결 완료");
			// 3. sql
			String sql = "delete from board where no =?";
			System.out.println("3. sql - " + sql);
			// 4. 실행객체
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);	//이 번호 가진 글 삭제
			System.out.println("4. 실행객체 - " + pstmt);			
			// 5. 실행
			int result = pstmt.executeUpdate();
			System.out.println("5. 실행 완료 - result : " + result);
			// 6. 표시
			System.out.println("6. 표시 - 게시판 글이 삭제되었습니다. - " + no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				System.out.println("7. 닫기 완료");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

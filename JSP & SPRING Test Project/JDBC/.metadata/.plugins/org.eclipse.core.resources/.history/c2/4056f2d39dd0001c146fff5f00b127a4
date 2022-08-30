package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.db.DB;

public class BoardView {

	public static void main(String[] args) {
		// DB 접속 정보

		// 사용할 객체
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버가 있는지 확인하고 클래스의 정보를 로딩(static)해야만 한다. 
			// -> 서버가 돌아갈때 맨처음 한번만 하면된다.

			Class.forName("board.DB");
			System.out.println("1. 드라이버확인 완료.");
			
			// 2. 서버 연결 - 서버, 사용자 정보
//			con = DriverManager.getConnection(url, UID, UPW);

			con = DB.getConenction();
			System.out.println("2. 오라클 서버 연결 완료. - " + con);
			
			//3. 실행할 SQL을 작성
			// ?한개는 데이터 한개를 의미한 실행전에 데이터와 바꿔치기를 한다.->데이터 세팅
			String sql = "select no, title, content, writer, writeDate, hit "
					+ " from board where no = ?";
			System.out.println("3. 실행할 SQL 문장 - " + sql);
			
			//4. 작성된 쿼리를 실행하기 위한 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			// set데이터타입(?의순서번호, 데이터타입의데이터)
			pstmt.setLong(1, 2);
			System.out.println("4. 실행 객체 설정 완료 : " + pstmt);
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			rs = pstmt.executeQuery();
			System.out.println("5. 실행 완료 : " + rs);
			
			// 6. 표시 또는 데이터 담기
			// next() - 다음 데이터가 있으면 true를 리턴해 준다. 작업 데이터의 포인터를 다음 데이터로 이동시켜준다.
			if(rs != null && rs.next()) {
				// rs.get데이터타입("select의 데이터 이름")
				System.out.println("번호 : " +rs.getLong("no"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("내용 : " + rs.getString("content"));
				System.out.println("작성자 : " + rs.getString("writer"));
				System.out.println("작성일 : " + rs.getString("writeDate"));
				System.out.println("조회수 : " + rs.getLong("hit"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 7. 사용한 객체 닫기
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

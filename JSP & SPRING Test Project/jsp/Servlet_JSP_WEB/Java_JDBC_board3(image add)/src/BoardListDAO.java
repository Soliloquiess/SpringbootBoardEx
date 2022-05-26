import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardListDAO {
	public static void main(String[] args) {

		// BoardVO객체 작성을 한다
		// 필요한 객체 선언을 해둔다. 그래야 7.닫기가 편해진다.

		// 연결객체
		Connection con = null;
		// 실행객체
		PreparedStatement pstmt = null;
		// 저장객체
		ResultSet rs = null;

		// 나중에 넘겨줄 데이터
		List<BoardVO> list = null;

		// DB(오라클) 접속 정보
		// 드라이버
		String driver = "oracle.jdbc.driver.OracleDriver"; // .class는 없어야 한다.
		// 서버
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		// 아이디
		String id = "java00";
		// 비번
		String pw = "java00";
		// 아래 1~7 사이 처리를 한개라도 오류 발생시, 7번 아래로 빠져나가야한다.->try{1~6}catch(){}finally{7}

		try {// 정상처리
				// 1. 오라클 드라이버가 있는지 확인
			Class.forName(driver); // static선언된 것은 로딩해준다.
			System.out.println("1.드라이버가 존재합니다.");
			// 2. 연결 - 서버, 아이디, 비번
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("2.오라클 연결 존재합니다.");
			// 3. 실행한 쿼리 작성 - 암기한 내용
			String sql = "select no, title, writer, writeDate,hit from board order by no desc";
			System.out.println("3. sql작성" + sql);
			// 4. 실행 객체- DB에 전달할 데이터 세팅
			pstmt = con.prepareStatement(sql);
			System.out.println("4.실행 객체 준비 완료");
			// 5. 실행과 결과 저장
			// 쿼리가 select- executeQuery()로 실행: 결과는 resultSet이 나온다.
			// 쿼리가 insert,update,delete, - executeUpdate로 실행: 결과는 Integer가 나온다.
			rs = pstmt.executeQuery();
			System.out.println("실행완료");
			// 6. 실행 결과 출력
			// rs객체가 있으면
			if (rs != null) {

				// rs.next(): rs안에 다음 데이터가 있으면 다음 데이터로 넘겨주고 true를 return 하는 메서드
				while (rs.next()) {
					// 다음 데이터가 있는 경우 반복 처리
					// 데이터를 저장할 객체 list가 null이면 생성한다 - 한번만 생성이 된다.
					if (list == null)
						list = new ArrayList<BoardVO>();
					// 한 줄의 데이터를 담을 BoardVO객체 생성 - 데이터 있는 만큼 한다.
					BoardVO vo = new BoardVO();
					// 출력 대신 vo객체 데이터를 담는다.-setter()사용
					vo.setNo(rs.getInt("no"));
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));

					// vo->list에 담기
					list.add(vo);
				} // end of while(rs.next());
//					//이 5줄 대신 vo에 담기 가능

				System.out.println("-----------------------");
				

				System.out.println("글번호 | 제목 | 글쓴이 | 작성일 | 조회수");
				System.out.println("-----------------------");
				// 향상된 for - list안에는 BoardVO객체가 들어있다.
				for (BoardVO vo : list) {
					System.out.print(vo.getNo() + " / "); // no는 colimnIndex
					System.out.print(vo.getTitle() + " / ");
					System.out.print(vo.getWriter() + " / ");
					System.out.print(vo.getWriteDate() + " / ");
					System.out.println(vo.getHit() + " / ");
				}
			}
			System.out.println("6. 출력완료");
		} catch (Exception e) {
			// 예외처리
			// 개발자 위한 예외 출력
			e.printStackTrace();

		} finally {
			// 반드시 처리 ->정상으로 실행되도 닫기가 필요., 예외 발생 하더라도 발생된 시점까지의 객체를 덮어야 한다.
			// 7. 사용한 객체 닫기
			try {
				// if(객체가 열려져 있는가) 열려져 있는 경우에만 닫는다.
				if (con != null)
					con.close(); // 연결객체 닫기
				if (pstmt != null)
					pstmt.close(); // 실행객체 닫기
				if (rs != null)
					rs.close(); // 저장객체 닫기.
				System.out.println("7.닫기 완료System.out.println(rs.getString(\"title\"+\"/\");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*별도의 도큐먼트 작성 기능 - 마우스를 올리면 보여지는 곳이 된다.
BoardViewDAO:게시판의 글보기 작성*/
public class BoardViewDAO {
	public static void main(String[] args) {
		//필요한 객체 선언
		//연결 객체
		Connection con = null; 
		//실행객체
		PreparedStatement pstmt = null;
		//결과 저장 객체
		ResultSet rs = null;
		
		//DB 서버에 연결할 때 사용되는 정보		
		// 드라이버
		String driver = "oracle.jdbc.driver.OracleDriver"; // .class는 없어야 한다.
		// 서버
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		// 아이디
		String id = "java00";
		// 비번
		String pw = "java00";
		//리턴 저장 객체 선언
		
		//보여줄 데이터 - 원래는 파라미터로 받는다.
		long no =100;
		
		//리턴 저장 객체 선언
		BoardVO vo = null;
		
		//프로그램 패턴
		try {	//정상	
			//1. 확인
			Class.forName(driver);
			//2. 연결
			con = DriverManager.getConnection(url, id, pw);
			
			//글 보기를 하기 위한 데이터를 가져오기 전에 조회수를 1 증가시킨다.
			//3.쿼리작성
			//?:값을 대체해야하는 부분에 대체문자
			String sql = "update board set hit = hit + 1 where no =?";
			
			//4.실행객체 & 데이터세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,no);
			
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			int result = pstmt.executeUpdate();
			
			//6.출력
			if(result!=0) System.out.println("조회수가 1 증가됨");
			else {
				System.out.println("조회수가 1 증가되지 않음 -  글 번호가 존재하지 않음");
				throw new Exception("조회수 1 증가 안됨. 글번호가 존재하지 않음.");
			}
			//7.닫기(pstmt)
			pstmt.close();
			//조회수 1 증가의 끝
			
			//3. 쿼리 작성
			 sql = "select no, title, content,writer, writeDate, hit from board where no = ?";
			//4. 실행 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no );	//이 1이 위의 쿼리 ?에 들어간다.
			//5. 실행
			//select - pstmt.executeQuery()- 결과가 result.
			//insert, update, delete - pstmt.executeUpdate -결과가 int
			
			rs = pstmt.executeQuery();
			
			//6. 출력- 데이터가 한개가 나오거나 또는 나오지 않는다.
			if(rs!=null&& rs.next()) {//데이터를 가져온 경우
				vo = new BoardVO();//저장할 vo 객체 생성
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}

			System.out.println("--------------------------");
			System.out.println("번호: "+ vo.getNo());
			System.out.println("제목: "+ vo.getTitle());
			System.out.println("내용: "+ vo.getContent());
			System.out.println("작성자: "+ vo.getWriter());
			System.out.println("작성일: "+ vo.getWriteDate());
			System.out.println("조회수: "+ vo.getHit());
			System.out.println("--------------------------");
		}catch(Exception e) {	//예외처리
			e.printStackTrace();
		}finally {	//반드시 처리
			try {	//정상 처리
				//7.닫기
				// if(객체가 열려져 있는가) 열려져 있는 경우에만 닫는다.
				if (con != null)
					con.close(); // 연결객체 닫기
				if (pstmt != null)
					pstmt.close(); // 실행객체 닫기
				if (rs != null)
					rs.close(); // 저장객체 닫기.
			}catch(Exception e) {	//예외처리
				e.printStackTrace();
			}
		}
	}
}

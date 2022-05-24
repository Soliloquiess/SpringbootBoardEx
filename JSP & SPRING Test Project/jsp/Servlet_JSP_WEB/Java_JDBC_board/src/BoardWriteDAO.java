import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BoardWriteDAO {

	// 데이터를 입력받는 객체
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		//데이터를 받는다 - 제목, 내용, 작성자
		System.out.println("제목 입력 -> ");
		String title = scanner.nextLine();
		System.out.println("내용 입력 -> ");
		String content = scanner.nextLine();
		System.out.println("작성ㅈ아 입력 -> ");
		String writer = scanner.nextLine();
		
		//BoardVO생성한 후 데이터 저장
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		System.out.println("BoardVO" + vo);
		
		//DB insert 처리를 한다
		//필요한 객체 선언 - insert -con, pstmt
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		//DB 정보
		// 드라이버
		String driver = "oracle.jdbc.driver.OracleDriver"; // .class는 없어야 한다.
		// 서버
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		// 아이디
		String id = "java00";
		// 비번
		String pw = "java00";
		//리턴되는 데이터 선언 : insert- int
		
		int result = 0;
		
		//jdbc 패턴
		
		try {
			//1. 확인
			Class.forName(driver);	//클래스 확인 하고 static으로 선언된 부분이 로딩된다.
			System.out.println("드라이버 확인 완료");
			//2. 연결
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 객체 생성 완료");
			
			//3.쿼리작성
			//?:값을 대체해야하는 부분에 대체문자
			String sql = "insert into board(no, title, content, writer) values (board_seq.nextval, ?, ?, ?)";
			
			//4.실행객체 & 데이터세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setString(3,vo.getWriter());
			System.out.println("실행 객체와 데이터 세팅 완료");
			
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			 result = pstmt.executeUpdate();
			
			//6.출력
			if(result>0) System.out.println("게시판 새로운 글 등록");
			System.out.println("출력 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//닫기
				if(con!=null) con.close();
				if(pstmt!=null) pstmt.close();
				System.out.println("닫기 완료");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

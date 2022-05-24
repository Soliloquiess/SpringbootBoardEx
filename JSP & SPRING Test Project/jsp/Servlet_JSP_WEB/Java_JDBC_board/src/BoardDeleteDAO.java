import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//삭제할 글 번호를 받아서 삭제 진행함.
public class BoardDeleteDAO {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		// 필요한 객체 선언 - con, pstmt
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
		

		// 수정을 위한 데이터 가져오기 객체 선언: BoardVO
		BoardVO vo = null;

		// 수정 결과를 저장하는 변수
		int result = 0;

		
		
		try {
			// 삭제할 글 번호 만들기
			System.out.println("삭제할 글 번호 입력");
			String data = scanner.nextLine();
			long no = Long.parseLong(data);
	
			
			

			// DB처리- delete 사용객체 2개 리턴타입 int
			
			
			
			//1. 확인
			Class.forName(driver);	//클래스 확인 하고 static으로 선언된 부분이 로딩된다.
			System.out.println("드라이버 확인 완료");
			//2. 연결
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 객체 생성 완료");
			
			//3.쿼리작성
			//?:값을 대체해야하는 부분에 대체문자
			String sql = "delete from board where no =?";
			System.out.println("sql"+sql);
			//4.실행객체 & 데이터세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,no);
			
			System.out.println("실행 객체와 데이터 세팅 완료");
			
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			 result = pstmt.executeUpdate();
				System.out.println("실행 완료");
			
			//6.출력
			if(result>0) System.out.println("게시판 "+no+"를 삭제하였습니다.");
			System.out.println("출력 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				
			}
		}
	}

}

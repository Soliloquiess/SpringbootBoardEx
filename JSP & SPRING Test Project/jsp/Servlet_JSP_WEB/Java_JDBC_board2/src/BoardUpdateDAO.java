import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BoardUpdateDAO {

	// 입력받는 객체 생성
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		// 필요한 객체 선언 - con, pstmt
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// DB 정보
		// 드라이버
		String driver = "oracle.jdbc.driver.OracleDriver"; // .class는 없어야 한다.
		// 서버
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		// 아이디
		String id = "java00";
		// 비번
		String pw = "java00";
		// 리턴되는 데이터 선언 : insert- int

		// 수정을 위한 데이터 가져오기 객체 선언: BoardVO
		BoardVO vo = null;

		// 수정 결과를 저장하는 변수
		int result = 0;

		try {
			
			// 수정할 글 번호 받기 -no
			System.out.println("수정할 글 번호 입력 ->");
			String data = scanner.nextLine();
			long no = Long.parseLong(data);
			System.out.println("수정할 글 번호" + no);
			// 수정할 글 번호에 해당되는 데이터 가져오기 BoardVO:1~7 ->view :BoardViewDAO

			//여기 아래 부분은 viewDAO에서 그대로 가져옴
			//1, 확인 - DB 처리시 한번만 하면 된다.
			Class.forName(driver);	
			System.out.println("데이터 가져오기 드라이버 확인");
			
			// 2. 연결
			con = DriverManager.getConnection(url, id, pw);

			System.out.println("데이터 가져오기 - 연결 완료");
			

			// 3. 쿼리 작성
			String sql = "select no, title, content,writer, writeDate, hit from board where no = ?";
			//위에서 no받은거로 세팅해서 사용하게 된다.
			System.out.println("데이터 가져오기 - 쿼리 작성");
			// 4. 실행 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no); // 이 1이 위의 쿼리 ?에 들어간다.
			System.out.println("데이터 가져오기- 실행객체 생성 & 데이터 세팅");
			// 5. 실행
			// select - pstmt.executeQuery()- 결과가 result.
			// insert, update, delete - pstmt.executeUpdate -결과가 int

			rs = pstmt.executeQuery();
			System.out.println("데이터 가져오기- 실행 완료");
			
			// 6. 출력- 데이터가 한개가 나오거나 또는 나오지 않는다.
			if (rs != null && rs.next()) {// 데이터를 가져온 경우
				vo = new BoardVO();// 저장할 vo 객체 생성
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
			else {
				System.out.println("없는 번호 입력하셨습니다.");
				System.exit(0);
			}

			
			
			System.out.println("--------------------------");
			System.out.println("번호: " + vo.getNo());
			System.out.println("제목: " + vo.getTitle());
			System.out.println("내용: " + vo.getContent());
			System.out.println("작성자: " + vo.getWriter());
			System.out.println("작성일: " + vo.getWriteDate());
			System.out.println("조회수: " + vo.getHit());
			System.out.println("--------------------------");
			
			System.out.println("가져온 데이터 출력 완료");
			
			//7. 닫기
			//7.닫기
			// if(객체가 열려져 있는가) 열려져 있는 경우에만 닫는다.
			if (con != null)
				con.close(); // 연결객체 닫기
			if (pstmt != null)
				pstmt.close(); // 실행객체 닫기
			if (rs != null)
				rs.close(); // 저장객체 닫기.
			//수정할 데이터 받아오기 끝 ---------------------------------
			
			
			// 가져온 데이터를 수정하기 - vo.setter() ->BOardWrieDAO-> 데이터 확인 수정 무한루프
			while(true) {
				
				//데이터 확인
				
				System.out.println(vo);
				//수정할 항목 선택
				//메뉴 출력
				System.out.println("1. 제목 2. 내용 3. 작성자 0.수정완료");
				System.out.println("수정할 항목 입력:");
				//메뉴 입력
				String menu= scanner.nextLine();
				//메뉴처리
				if(menu.equals("1")) {
					System.out.println("제목");
					vo.setTitle(scanner.nextLine());
				}else if(menu.contentEquals("2")) {
					System.out.println("내용:");
					vo.setContent(scanner.nextLine());
				}else if(menu.contentEquals("3")) {
					System.out.println("작성자:");
					vo.setWriter(scanner.nextLine());
				}else if(menu.contentEquals("0")) {
					break;
				}else {
					System.out.println("항목 잘못 입력");
				}	//if else
			}	//end of while(true)= 데이터 수정 입력
			
			System.out.println("데이터 수정하기 완"+vo);
			// 수정할 데이터 DB 적용하기 - insert : 1~7 insert,update, delete, 비슷한 류의 프로그램 -> 복붙 후 약간의  수정 거침 -boardWriteDAO에서 수정할 것
		
		
		
			//1. 확인
			Class.forName(driver);	//클래스 확인 하고 static으로 선언된 부분이 로딩된다.
			System.out.println("드라이버 확인 완료");
			//2. 연결
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 객체 생성 완료");
			
			//3.쿼리작성
			//?:값을 대체해야하는 부분에 대체문자
			sql = "insert into board(no, title, content, writer) values (board_seq.nextval, ?, ?, ?)";
			
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
			
		
		
		
			
			
			
			
			
			
			
			// BoardWriteDAO에서 가져온 업데이트 부분----------------------------------------------------------
			
			
			//1. 확인(위에서 함)
			//Class.forName(driver);	//클래스 확인 하고 static으로 선언된 부분이 로딩된다.
			//System.out.println("드라이버 확인 완료");
			//2. 연결
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("db수정 연결 객체 생성 완료");
			
			//3.쿼리작성
			//?:값을 대체해야하는 부분에 대체문자
			 sql = "update board set title = ?, content = ?, writer=? where no = ?";

				System.out.println("db수정 sql");
			//4.실행객체 & 데이터세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setString(3,vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			System.out.println("실행 객체와 데이터 세팅 완료");
			
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			 result = pstmt.executeUpdate();

				System.out.println("실행 완료");
			//6.출력
			if(result>0) System.out.println("게시판 글"+vo.getNo()+"이 등록");
			
			System.out.println("출력 완료");
			

			// BoardWriteDAO에서 가져온 업데이트 부분----------------------------------------------------------
			
			
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception("게시판 글수정 - 글수정 중 DB 오류");
		} finally

		{
			try {

				// 7.닫기
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

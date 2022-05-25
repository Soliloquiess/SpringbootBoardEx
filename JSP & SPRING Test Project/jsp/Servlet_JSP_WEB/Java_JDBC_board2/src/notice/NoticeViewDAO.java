package notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//공지사항 글보기 -> 보여줄 글 번호 입력 -> 데이터 가져와서 출력
public class NoticeViewDAO {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// 필요한 객체 선언 -select : con, pstmt, rs

		// 연결객체
		Connection con = null;
		// 실행객체
		PreparedStatement pstmt = null;
		// 저장객체
		ResultSet rs = null;

		
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

		
		// 결과로 나온 데이터 선언: select : NoticeVO	//리스트가 아닌 게시판 하나 보는거니까 NoticeVO
		NoticeVO vo = null;
		

	
		// 보여줄 공지 글 번호 선택 - 입력
			System.out.println("공지 글 번호의 선택 ");
			String data = scanner.nextLine();	//공지 번호 입력시 숫자로 잘 입력해야 됨
			// 선택되어진 데이터 가져오기
			long no  = Long.parseLong(data);	
			
			
			// 선택되어진 데이터 가져오기
		try {// 정상 처리 1~6

			//1.확인
			Class.forName(driver);
			//2.연결
			con=DriverManager.getConnection(url,id,pw);
			//3.쿼리작성
			//1)원본 데이터 가져오기
//			String sql = "select no, title, content, TO_CHAR(startDate, 'yyyy.mm.dd') startDate, TO_CHAR(endDate, 'yyyy.mm.dd') endDate, TO_CHAR(updateDate, 'yyyy-mm.dd')update from notice where no=?";
//			//2)가져온 원본 데이터에 순서 번호 붙이기 (RowNum rnum)
//			sql = "select ROWNUM rnum, no, title, startDate, endDate, updateDate from ("+sql+")";
//			//3)순서 번호를 붙인 데이터에서 페이지에 맞는 데이터 가져오기
			String sql = "select no, title, content, TO_CHAR(startDate, 'yyyy.mm.dd') startDate, TO_CHAR(endDate, 'yyyy.mm.dd') endDate, TO_CHAR(updateDate, 'yyyy.mm.dd') updateDate from notice where no = ?";
			System.out.println("sql"+ sql);
			
			//4.실행객체, 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			System.out.println("실행객체 생성");
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			rs = pstmt.executeQuery();
			
			//6.출력(list->출력) 데이터가 여러개:반복문
			//1)데이터를 NoticeVO에 담기
			if(rs!=null && rs.next()) {//결과 저장 객체가 null이 아니고, 데이터가 있으면
					//rs->vo로 데이터 담기.
					//rs로 vo객체 담기-> 리스트에 담는다
					//데이터를 담기 위해 리스트가 null이면 안된다. 한번만생성 -생성이 안되어있으면 생성한다.
					 vo = new NoticeVO();//생성한 후 저장한다.
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					
				//데이터 확인
				System.out.println("db에서 가져온 데이터 확인"+vo);
			}//end of if(rs!=null)
		
		
			//2)vo에 있는 데이터 출력
			
			System.out.println("-----------------------");
			

			System.out.println("글번호 | 제목 | 공지 시작일 | 공지 종료일 | 최종 수정일");
			System.out.println("-----------------------");

			
			//데이터 없는 경우 처리- list가 널이 나온다.
			if(vo==null) {	//데이터가 없다.
			System.out.println("데이터가 존재하지 않습니다.");
			
			}else {	//데이터가 있다.
					System.out.print(vo.getNo() + " / "); // no는 colimnIndex
					System.out.print(vo.getTitle() + " / ");
					System.out.print(vo.getContent() + " / ");
					
					System.out.print(vo.getStartDate() + " / ");
					System.out.print(vo.getEndDate() + " / ");
					System.out.print(vo.getUpdateDate() + " / ");
					
					System.out.println();//줄바꿈
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

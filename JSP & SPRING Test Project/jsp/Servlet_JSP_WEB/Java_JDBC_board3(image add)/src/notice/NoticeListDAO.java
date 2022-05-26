package notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoticeListDAO {

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

		
		// 결과로 나온 데이터 선언: select : List<NoticeVO>
		List<NoticeVO> list = null;
		
		//페이지 처리를 위한 변수: 1페이지에 해당하는 데이터로  세팅
		long startRow= 1;
		long endRow = 10;
		

		// 공지 쿼리 뒤에 붙는 조건문
		String condition = "";

		// 공지 리스트 종류 선택 - 입력
		while (true) {
			System.out.println("공지 리스트 종류 선택");
			System.out.println("1.전체 공지 2. 현재 공지 3. 지난 공지 4. 예약 공지");
			System.out.println("공지 선택 1~4");
			String menu = scanner.nextLine();
			// 선택되어진 데이터 가져오기

			if (menu.equals("1")) {
				// 조건이 없으면 전ㅊ네 공지가 된다.
				break;
			} else if (menu.equals("2")) {
				// 현재 공지 현재 날짜가 공지 시작일과 종료일 사이에 있다.
				condition = "where startDate< sysdate and endDate > trunc(sysdate)";
				// where문 선택되면 빠져나감(break로)
				break;
			} else if (menu.equals("3")) {
				// 지난 공지 - 종료일이 현재 날짜보다 작다.
				condition = "where endDate <  trunc(sysdate)";

				break;
			} else if (menu.equals("4")) {
				// 예약 공지 - 시작일이 현재 날짜보다 크다.
				condition = "where startDate > trunc(sysdate)";

				break;
			} else
				System.out.println("잘못된 공지 종류");
		} // end of while(true)
		System.out.println("공지사항을 위한 처리문:" + condition);
		// 선택되어진 데이터 가져오기
		try {// 정상 처리 1~6

			//1.확인
			Class.forName(driver);
			//2.연결
			con=DriverManager.getConnection(url,id,pw);
			//3.쿼리작성
			//1)원본 데이터 가져오기
			String sql = "select no, title, startDate,endDate, updateDate from notice " + condition + "order by startDate desc";
			//2)가져온 원본 데이터에 순서 번호 붙이기 (RowNum rnum)
			sql = "select ROWNUM rnum, no, title, startDate, endDate, updateDate from ("+sql+")";
			//3)순서 번호를 붙인 데이터에서 페이지에 맞는 데이터 가져오기
			sql = "select rnum, no, title, TO_CHAR(startDate, 'yyyy.mm.dd') startDate, TO_CHAR(endDate, 'yyyy.mm.dd') endDate, TO_CHAR(updateDate, 'yyyy.mm.dd') updateDate "
					+ "from (" +sql+")"
					+"where rnum between ? and ?"; //페이지 조건
			System.out.println("sql"+ sql);
			
			//4.실행객체, 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, startRow);
			pstmt.setLong(2, endRow);
			
			//5.실행
			//select - pstmt.executeQuery() - 결과가 ResultSet
			//insert, update, delete - pstmt.executeUpdate = 결과가 int
			
			rs = pstmt.executeQuery();
			
			//6.출력(list->출력) 데이터가 여러개:반복문
			//1)데이터를 list에 담기
			if(rs!=null) {//결과 저장 객체가 null이 아니다
				while(rs.next()) {	//다음 데이터가 있으면 반복 처리
					//rs로 vo객체 담기-> 리스트에 담는다
					//데이터를 담기 위해 리스트가 null이면 안된다. 한번만생성 -생성이 안되어있으면 생성한다.
					if(list==null) list  = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();//생성한 후 저장한다.
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					//vo->list
					list.add(vo);
				}//end of while(rs.next())
				//데이터 확인
				System.out.println("db에서 가져온 데이터 확인"+list);
			}//end of if(rs!=null)
			//2)list에 있는 데이터 출력
			
			System.out.println("-----------------------");
			

			System.out.println("글번호 | 제목 | 공지 시작일 | 공지 종료일 | 최종 수정일");
			System.out.println("-----------------------");

			
			//데이터 없는 경우 처리- list가 널이 나온다.
			if(list==null) {	//데이터가 없다.
			System.out.println("데이터가 존재하지 않습니다.");
			
			}else {	//데이터가 있다.
				// 향상된 for - list안에는 BoardVO객체가 들어있다.
				for (NoticeVO vo : list) {
					System.out.print(vo.getNo() + " / "); // no는 colimnIndex
					System.out.print(vo.getTitle() + " / ");
					System.out.print(vo.getStartDate() + " / ");
					System.out.print(vo.getEndDate() + " / ");
					System.out.print(vo.getUpdateDate() + " / ");
					System.out.println();//줄바꿈
				}
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

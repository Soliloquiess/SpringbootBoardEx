package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.board.vo.BoardVO;
import com.util.db.DB;

public class BoardDAO {

	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;

	// 게시판 리스트 처리 메서드
	public List<BoardVO> list() {
		System.out.println("boarddao.list()");
		List<BoardVO> list = null;

		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.sql
			String sql = "select no, title, writer, writeDate, hit from board order by no desc";

			// 4.실행객체
			pstmt = con.prepareStatement(sql);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6.표시 또는 담기
			if (rs != null) {
				// 다음글이 있다면 다음글로
				while (rs.next()) {
					// 만약 데이터 담을 리스트가 널이면 못 담아서 한번은 생성해야 됨.(뺴면 안됨)
					if (list == null)
						list = new ArrayList<BoardVO>();
					// 실제적인 데이터 담을 객체 생성
					BoardVO vo = new BoardVO();
					// setter이용해서 데이터 담기 시전
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));

					// vo를 list에 담기

					list.add(vo);

				}
			} // end of if(rs!=null)
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of try

		System.out.println("BoardDAO.list().list- " + list);
		return list;
	}

//	public void view(long no) {
//		// TODO Auto-generated method stub
//		
//	}

	// 2-1. 글보기

	// BoardVO리턴할 거
	public BoardVO view(long no) throws Exception {
		// 콘솔에서 확인해야 할 내용.
		BoardVO vo = null;

		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. sql
			String sql = "select no, title, content, writer, writeDate, hit from board where no =?";
//			String sql = "select no, title, content, writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
//					+ " hit from board where no = ?";
//				System.out.println("BoardDAO.view().sql - " + sql);
			// 4. 실행객체 && 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no); // 첫번째 데이터
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 / 데이터 담기
			if (rs != null && rs.next()) {
				// 데이터가 있으면 vo 를 생성한다.
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("게시판 글보기 - 데이터 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}

		return vo; // 리턴하고 서비스로 돌아감
	} // view()의 끝

	// 3. 글쓰기 - 처리문 들어 있는 메서드 작성
	public int write(BoardVO vo) throws Exception {
		int result = 0;

		// JDBC 프로그램 형태
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. sql
			String sql = "insert into board(no, title, content, writer) " + " values(board_seq.nextval, ?, ?, ?)";
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			// 5. 실행 - insert
			result = pstmt.executeUpdate();
			// 6. 데이터 표시 또는 담기
			System.out.println("게시판 글등록이 되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("게시판 글쓰기 - 글쓰기 중 DB 오류");
		} finally {
			// 7. 닫기
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 4. 글수정
	public int update(BoardVO vo) throws Exception {
		System.out.println("BoardDAO.update().vo - " + vo);
		int result = 0;

		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. sql
			String sql = "update board set title = ?, content = ?, writer = ? where no = ?";
			// 4. 실행객체 & 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과표시 & 데이터 담기
			if (result == 1)
				System.out.println("BoardDAO.update()- 글 수정 완료");
			else
				System.out.println("BoardDAO.update()- 글 수정 실패");

//			if (result == 0)
//				System.out.println("수정할 데이터가 존재하지 않습니다.");
//			else
//				System.out.println("데이터 수정이 완료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("게시판 글수정 - 글수정 중 DB 오류");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		return result;
	}

	// 5. 글삭제
		public int delete(long no)  throws Exception {
			System.out.println("BoardDAO.delete().no - " + no);
			int result = 0;
			
			// JDBC 프로그램
			try {
				// 1. 드라이버 확인 + 2. 연결
				con = DB.getConnection();
				// 3.
				String sql = "delete from board where no = ?";
				// 4.
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, no);
				// 5.
				result = pstmt.executeUpdate();
				// 6. 
				
				if(result == 1) System.out.println(no+"번 데이터 삭제");
				else System.out.println("삭제 오류 - 글번호 없음. no="+no);
//				if(result == 0) System.out.println("정보를 확인해 주세요.");
//				else System.out.println("데이터가 삭제 되었습니다.");
			
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("게시판 글삭제 - 글삭제 중 DB 오류");
			} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			}
			
			
			return result;
		}
}

//	// 사용할 객체
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//
//	// 게시판 리스트
//	public List<BoardVO> list() throws Exception {
//		List<BoardVO> list = null;
//
//		// 예외처리
//		try {
//			// 1.2
//			con = DB.getConenction();
//			// 3.sql
//			String sql = "select no, title, writer, writerDate, hit from board order by desc";
//			// 4.
//			pstmt = con.prepareStatement(sql);
//			// 5.
//			rs = pstmt.executeQuery();
//			// 6.
//			if (rs != null) {
//				while (rs.next()) {
//					if (list == null)
//						list = new ArrayList<>();
//					BoardVO vo = new BoardVO();
//					vo.setNo(rs.getLong("no"));
//					vo.setTitle(rs.getString("title"));
//					vo.setWriter(rs.getString("writer"));
//					vo.setWriteDate(rs.getString("writeDate"));
//					vo.setHit(rs.getLong("hit"));
//					
//
//					// vo를 list에 담기
//
//					list.add(vo);
//				}
//			}
//
//		} catch (Exception e) {
//
//		} finally {
//			try {
//				// 7.닫기
//				DB.close(con, pstmt, rs);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
//}

package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.DBInfo;
import com.webjjang.util.sql.DAOSQL;

public class BoardDAO {

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 게시판 리스트 -----------------------------
	public List<BoardVO> list() throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		List<BoardVO> list = null;
		
		// DB의 정보는 DBInfo 객체이 넣어놨다.
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_LIST);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_LIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 출력 -> 데이터 저장 후 리턴
			if(rs != null) {
				while(rs.next()) {
					// list null 이면 생성해야한다. - 한번만
					if(list == null) list = new ArrayList<>();
					// rs -> vo 저장을 위해 vo 객체 생성
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					// vo -> list로 저장
					list.add(vo);
				} // end of while
			} // end of if()
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 리스트 데이터 처리 중 DB오류가 발생되었습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of list()
	
	
	
	// 게시판 글보기 -----------------------------
	public BoardVO view(long no) throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		BoardVO vo = null;
		
		// DB의 정보는 DBInfo 객체이 넣어놨다.
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_VIEW);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_VIEW);
			pstmt.setLong(1, no);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 출력 -> 데이터 저장 후 리턴 -> 데이터 한개
			if(rs != null & rs.next()) {
				// rs -> vo 저장을 위해 vo 객체 생성
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				
			} // end of if()
			
			return vo;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 글보기 데이터 처리 중 DB오류가 발생되었습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of view()

	
	// 게시판 글보기 조회수 1 증가 -----------------------------
	public int increase(long no) throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_INCREASE);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_INCREASE);
			pstmt.setLong(1, no);
			// 5. 실행 - update -> 결과가 int
			result = pstmt.executeUpdate();
			// 6. 출력 -> 데이터 저장 후 리턴 -> 데이터 한개
			System.out.println("BoardDAO.increase() : 글보기 조회수 1증가 완료");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 글보기 조회수 1증가 처리 중 DB오류가 발생되었습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of increase()
	
	
	// 게시판 글쓰기 -----------------------------
	public int write(BoardVO vo) throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_WRITE);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			// 5. 실행 - insert -> 결과가 int
			result = pstmt.executeUpdate();
			// 6. 출력 -> 데이터 저장 후 리턴 -> 데이터 한개
			System.out.println("BoardDAO.write() : 글쓰기 완료");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("게시판 글쓰기 처리 중 DB오류가 발생되었습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of write()
	
	
	// 게시판 글수정 -----------------------------
	public int update(BoardVO vo) throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_UPDATE);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			// 5. 실행 - update -> 결과가 int
			result = pstmt.executeUpdate();
			// 6. 출력 -> 데이터 저장 후 리턴 -> 데이터 한개
			System.out.println("BoardDAO.update() : 글수정 완료");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace(); // 게발자를 위한 출력
			throw new Exception("게시판 글쓰기 처리 중 DB오류가 발생되었습니다."); // BoardController에서 예외처리를 위한 처리
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of update()
	
	
	// 게시판 글삭제 -----------------------------
	public int delete(long no) throws Exception{
		
		// 실행한 결과를 저장하는 객체 선언
		int result = 0;
		
		try {
			//1. 드라이버 확인 - DBInfo의 static 초기화 블록에서 처리함. - main() 처음 시작할때 처리 : 한번만
			//2. 연결객체
			con = DBInfo.getConnection();
			//3. 쿼리 작성 - DAOSQL에 선언됨. - 확인
			System.out.println(DAOSQL.BOARD_DELETE);
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(DAOSQL.BOARD_DELETE);
			pstmt.setLong(1, no);
			// 5. 실행 - delete -> 결과가 int
			result = pstmt.executeUpdate();
			// 6. 출력 -> 데이터 저장 후 리턴 -> 데이터 한개
			System.out.println("BoardDAO.delete() : 글삭제 완료");
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace(); // 게발자를 위한 출력
			throw new Exception("게시판 글삭제 처리 중 DB 오류가 발생되었습니다."); // BoardController에서 예외처리를 위한 처리
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // end of try~catch ~finally
		
	} // end of delete()
	
	
}

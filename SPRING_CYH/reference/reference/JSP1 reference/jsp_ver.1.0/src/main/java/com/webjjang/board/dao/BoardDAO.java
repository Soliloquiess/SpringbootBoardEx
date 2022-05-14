package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.db.DB;

public class BoardDAO {

	// 필요한 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<BoardVO> list() throws Exception{
		List<BoardVO> list = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "select no, title, writer, writeDate, hit from board order by no desc";
			//4 
			pstmt = con.prepareStatement(sql);
			// 5
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<BoardVO>();
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			throw new Exception("게시판 리스트 DB 처리 중 오류가 발생되었습니다.");
		} finally {
			try {
				// 7.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	public BoardVO view(long no) {
		// TODO Auto-generated method stub
		BoardVO vo = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "select no, title, content, writer, writeDate, hit "
					+ " from board where no = ?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null && rs.next()) {
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
		} finally {
			try {
				//7.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}

	public int write(BoardVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			// 1.2.
			con = DB.getConnection();
			// 3.
			String sql = "insert into board(no, title, content, writer) "
					+ " values(board_seq.nextval, ?, ?, ?)";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			//5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("게시판 글등록 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int update(BoardVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			//1.2
			con = DB.getConnection();
			// 3.
			String sql = "update board set title = ?, content = ?, writer = ? where no = ?";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			// 5.
			result = pstmt.executeUpdate();
			// 6
			if(result == 1)
				System.out.println("BoardDAO.update() - 글수정 완료");
			else
				System.out.println("BoardDAO.update() - 글수정 실패 : 글번호 틀림.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int delete(long no) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "delete from board where no = ?";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			//5
			result = pstmt.executeUpdate();
			// 6
			if(result == 1) System.out.println(no + "번 데이터 삭제");
			else System.out.println("삭제 오류 - 글번호 없음. no = " + no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// 조회수 1 증가 : list -> view
	public void increase(long no) throws Exception {
		// TODO Auto-generated method stub
		try {
			//1.2.
			con = DB.getConnection();
			//3
			String sql = "update board set hit = hit + 1 where no = ?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5.
			pstmt.executeUpdate();
			//6.
			System.out.println(no + "번 글의 조회수가 1증가 되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

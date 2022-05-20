package com.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.notice.vo.NoticeVO;
import com.util.PageObject;
import com.util.db.DB;

public class NoticeDAO {

	// 필요한 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// pt - 공지의 종류 = pageObject.period
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
		List<NoticeVO> list = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			// 3-1. 원본 데이터
			String sql = "SELECT no, title, " 
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate,"
//			+ " updateDate FROM notice ";
			+ " to_char(updateDate, 'yyyy-mm-dd') updateDate FROM notice ";
			// 조건에 맞는 쿼리 추가. -> 동적 쿼리 : 넘어오는 pt 변수의 값으로 정한다.
			switch (pageObject.getPeriod()) {
			case "now":
				sql += " WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE) ";
				break;
			case "old":
				sql += " WHERE endDate < TRUNC(SYSDATE) ";
				break;
			case "res":
				sql += " WHERE startDate > SYSDATE ";
				break;
			case "all":
				sql += " ";
				break;

			default:
				System.out.println("잘못된 정보가 넘어 왔습니다.");// 잘못된 데이터 일 경우 현재로 작성한다.
				sql += " WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE) ";
				break;
			}
			
			// 정렬
			sql += " ORDER BY updateDate DESC, no DESC ";
			
			//3-2. 순서 번호 붙이기
			sql = "SELECT rownum rnum, no, title, startDate, endDate, updateDate from( " + sql
					+ ")";
			
			// 3-3. 페이지에 맞는 데이터 가져오기
			sql = "SELECT rnum, no, title, startDate, endDate, updateDate from( " + sql
					+ ") where rnum between ? and ?";
			
			System.out.println("NoticeDAO.list().sql : " + sql);
			
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			// 5
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

	// 페이지 처리를 위해서 전체 데이터 갯수를 가져오는 메서드
	public long getTotalRow(PageObject pageObject) {
		// TODO Auto-generated method stub
		long totalRow = 0;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "select count(*)  from notice ";
			// 조건에 맞는 쿼리 추가. -> 동적 쿼리 : 넘어오는 pt 변수의 값으로 정한다.
			switch (pageObject.getPeriod()) {
			case "now":
				sql += " WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE) ";
				break;
			case "old":
				sql += " WHERE endDate < TRUNC(SYSDATE) ";
				break;
			case "res":
				sql += " WHERE startDate > SYSDATE ";
				break;
			case "all":
				sql += " ";
				break;

			default:
				System.out.println("잘못된 정보가 넘어 왔습니다.");// 잘못된 데이터 일 경우 현재로 작성한다.
				sql += " WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE) ";
				break;
			}
			

			// 4.
			pstmt = con.prepareStatement(sql);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
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
		return totalRow;
	}


	
	public NoticeVO view(long no) {
		// TODO Auto-generated method stub
		NoticeVO vo = null;
		
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " SELECT no, title, content, "
					+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
					+ " to_char(endDate, 'yyyy-mm-dd') endDate, "
					+ " to_char(updateDate, 'yyyy-mm-dd') updateDate "
					+ " FROM notice WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}

	public int write(NoticeVO vo) {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " INSERT INTO notice(no, title, content, startDate, endDate) "
					+ " VALUES(notice_seq.nextval, ?, ?, ?, ?) ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			// 5
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("Notice.write() - 공지사항 등록 완료");
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

	public int update(NoticeVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " UPDATE notice SET title = ?, content = ?, startDate = ?, endDate = ? "
					+ " WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setLong(5,  vo.getNo());
			// 5
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("Notice.update() - 공지사항 수정 완료");
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
			// 3.
			String sql = " DELETE FROM notice WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,  no);
			// 5
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("Notice.delete() - 공지사항 삭제 완료");
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

}

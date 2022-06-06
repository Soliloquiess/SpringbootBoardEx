package com.webjjang.notice.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DAO;
import com.webjjang.util.db.DBInfo;

public class NoticeDAO extends DAO {

	// 1. 전체 데이터 가져오기
	public long getTotalRow(PageObject pageObject) throws Exception {
		
		System.out.println(this.getClass().getSimpleName() + "." 
		+ new Object(){}.getClass().getEnclosingMethod().getName() + "()");
		System.out.println(this.getClass().getEnclosingMethod());
		System.out.println(new Object(){}.getClass().getEnclosingMethod());
		long cnt = 0;
		
		try { // 정상처리
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql 작성
			String sql = "select count(*) from notice";
//			System.out.println("NoticeDAO.getTotalRow().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			// 5. 실행
			// pstmt.executeQuery() - select 문 --> ResultSet
			// pstmt.executeUpdate() - insert, update, delete 문 --> int
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("공지사항의 전체 글의 개수 : " + cnt);
			} else System.out.println("데이터가 존재하지 않습니다.");
		}catch (Exception e) { // 예외처리
			e.printStackTrace(); // 오류 출력 - 개발자를 위한 오류 출력
			// 메서드 선언한 부분 뒤에 throws 에 추가하거나 catch를 더 잡아 주면 된다.
			throw new Exception("공지사항 리스트 - 전체 데이터 개수 가져오기 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // try~catch~finally의 끝
		
		return cnt;
	}
	
	// 1-1.리스트 - MainController - NoticeController - NoticeListService - NoticeDAO
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
//		System.out.println("NoticeDAO.list()");
		List<NoticeVO> list = null;
		
		try {// 정상처리
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql 작성 - 페이지 처리된 데이터 1페이지
			String sql = " select no, title, startDate, endDate, updateDate "
					+ " from notice order by startDate desc  ";
			sql = " select rownum rnum, no, title, startDate, endDate, updateDate from( " + sql + ") ";
			sql = " select rnum, no, title, "
					+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
					+ " to_char(endDate, 'yyyy.mm.dd') endDate, "
					+ " to_char(updateDate, 'yyyy.mm.dd') updateDate "
					+ " from (" + sql
					+ ") where rnum between ? and ?";
//			System.out.println("NoticeDAO.list().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow()); // 시작 번호 - 1 :1페이지 정보
			pstmt.setLong(2, pageObject.getEndRow()); // 끝 번호 - 10 : 1페이지 정보
//			System.out.println("NoticeDAO.list() - 실행 객체 생성 완료.");
			// 5. 실행
			rs = pstmt.executeQuery();
//			System.out.println("NoticeDAO.list() - 실행 완료.");
			// 6. 표시 & 담기
			// rs -> noticeVO(생성->데이터담기) 데이터 있는 만큼 반복처리:while -> list(list가 null이면 생성한다.)
			if(rs != null) {
				// 데이터가 있으면 있는 만큼 반복처리한다.
				while(rs.next()) {
					// list가 null이면 생성한다. 처음 한번만 실행이된다.
					if(list == null) list = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					
					// list 에 담는다.
					list.add(vo);
				} // while의 끝
			}// if문의 끝
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력 - 개발자를 위한 오류 출력
			throw new Exception("공지사항 리스트 데이터 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 가져온 데이터를 확인한다.
//		System.out.println("NoticeDAO.list().list - " + list);
		
		return list;
	} // list()의 끝-------------------------------
	
	
	// 2. 조회수 1 증가
	public int increase(long no) throws Exception {
		
		int result = 0;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "update notice set hit = hit + 1 where no = ?";
//			System.out.println("NoticeDAO.increase().sql - " + sql);
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 표시
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력
			throw new Exception("공지사항 글보기 - 조회수 1증가 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
	
	// 2-1. 글보기
	public  NoticeVO view(long no) throws Exception {
		// 콘솔에서 확인해야 할 내용.
		NoticeVO vo = null;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "select no, title, content, writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
					+ " hit from notice where no = ?";
//			System.out.println("NoticeDAO.view().sql - " + sql);
			// 4. 실행객체 && 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 / 데이터 담기
			if(rs != null && rs.next()) {
				// 데이터가 있으면 vo 를 생성한다.
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriteDate(rs.getString("writeDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글보기 - 데이터 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	} // view()의 끝
	
	// 3. 글쓰기 - 처리문 들어 있는 메서드 작성	
	public int write(NoticeVO vo)  throws Exception {
		int result = 0;
		
		// JDBC 프로그램 형태
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "insert into notice(no, title, content, writer) "
					+ " values(notice_seq.nextval, ?, ?, ?)";
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			// 5. 실행 - insert 
			result = pstmt.executeUpdate();
			// 6. 데이터 표시 또는 담기
			System.out.println("공지사항 글등록이 되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글쓰기 - 글쓰기 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	// 4. 글수정
	public int update(NoticeVO vo)  throws Exception {
		System.out.println("NoticeDAO.update().vo - " + vo);
		int result = 0;
		
		//JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "update notice set title = ?, content = ?, writer = ? where no = ?";
			// 4. 실행객체 & 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(4, vo.getNo());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과표시 & 데이터 담기
			if(result == 0) System.out.println("수정할 데이터가 존재하지 않습니다.");
			else System.out.println("데이터 수정이 완료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글수정 - 글수정 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
	
	// 5. 글삭제
	public int delete(long no)  throws Exception {
		System.out.println("NoticeDAO.delete().no - " + no);
		int result = 0;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3.
			String sql = "delete from notice where no = ?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5.
			result = pstmt.executeUpdate();
			// 6. 
			if(result == 0) System.out.println("정보를 확인해 주세요.");
			else System.out.println("데이터가 삭제 되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글삭제 - 글삭제 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
}

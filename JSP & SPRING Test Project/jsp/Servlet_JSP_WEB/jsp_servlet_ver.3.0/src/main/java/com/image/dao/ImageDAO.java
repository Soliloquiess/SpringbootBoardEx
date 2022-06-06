package com.image.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.image.vo.ImageVO;
import com.util.db.DAO;
import com.util.db.DBInfo;
import com.webjjang.util.PageObject;

public class ImageDAO extends DAO {

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
			String sql = "select count(*) from image ";
			if(pageObject.getWord() != null && !pageObject.getWord().equals(""))
				sql += search(pageObject);
			System.out.println("ImageDAO.getTotalRow().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			// 조건이 있는 경우 데이터 세팅을 해야한다.
			idx = searchSetData(pageObject, pstmt, idx);
			
			// 5. 실행
			// pstmt.executeQuery() - select 문 --> ResultSet
			// pstmt.executeUpdate() - insert, update, delete 문 --> int
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("이미지 게시판의 전체 글의 개수 : " + cnt);
			} else System.out.println("데이터가 존재하지 않습니다.");
		}catch (Exception e) { // 예외처리
			e.printStackTrace(); // 오류 출력 - 개발자를 위한 오류 출력
			// 메서드 선언한 부분 뒤에 throws 에 추가하거나 catch를 더 잡아 주면 된다.
			throw new Exception("이미지 게시판 리스트 - 전체 데이터 개수 가져오기 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // try~catch~finally의 끝
		
		return cnt;
	}
	
	// 1-1.리스트 - MainController - ImageController - ImageListService - ImageDAO
	public List<ImageVO> list(PageObject pageObject) throws Exception{
//		System.out.println("ImageDAO.list()");
		List<ImageVO> list = null;
		
		// 검색을 하는 경우
		boolean searchCondition = pageObject.getWord() != null && !pageObject.getWord().equals("");
		
		try {// 정상처리
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql 작성 - 페이지 처리된 데이터 1페이지
			String sql = " select i.no, i.title, i.id, m.name, i.writeDate, i.fileName "
					+ " from image i, member m " + search(pageObject)
					+ ((searchCondition)?" and (i.id = m.id) ":" where i.id = m.id ")
					+ " order by no desc ";
			sql = " select rownum rnum, no, title, id, name, writeDate, fileName from( " + sql + ") ";
			sql = " select rnum, no, title, id, name, "
					+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, fileName from (" + sql
					+ ") where rnum between ? and ?";
			System.out.println("ImageDAO.list().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			// 조건에 해당되는 pstmt 세팅
			idx = searchSetData(pageObject, pstmt, idx);
			pstmt.setLong(idx++, pageObject.getStartRow()); // 시작 번호 - 1 :1페이지 정보
			pstmt.setLong(idx++, pageObject.getEndRow()); // 끝 번호 - 10 : 1페이지 정보
//			System.out.println("ImageDAO.list() - 실행 객체 생성 완료.");
			// 5. 실행
			rs = pstmt.executeQuery();
//			System.out.println("ImageDAO.list() - 실행 완료.");
			// 6. 표시 & 담기
			// rs -> imageVO(생성->데이터담기) 데이터 있는 만큼 반복처리:while -> list(list가 null이면 생성한다.)
			if(rs != null) {
				// 데이터가 있으면 있는 만큼 반복처리한다.
				while(rs.next()) {
					// list가 null이면 생성한다. 처음 한번만 실행이된다.
					if(list == null) list = new ArrayList<ImageVO>();
					ImageVO vo = new ImageVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setFileName(rs.getString("fileName"));
					
					// list 에 담는다.
					list.add(vo);
				} // while의 끝
			}// if문의 끝
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력 - 개발자를 위한 오류 출력
			throw new Exception("이미지 게시판 리스트 데이터 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 가져온 데이터를 확인한다.
//		System.out.println("ImageDAO.list().list - " + list);
		
		return list;
	} // list()의 끝-------------------------------
	
	
	// 1-2. 검색에 대한 문자열을 붙이는 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private String search(PageObject pageObject) {
		String condition = "";
		
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			condition += " where ( 1 = 0 ";
			if(pageObject.getKey().indexOf("t") != -1)
				condition += " or title like ? ";
			if(pageObject.getKey().indexOf("c") != -1)
				condition += " or content like ? ";
			if(pageObject.getKey().indexOf("w") != -1)
				condition += " or writer like ? ) ";
		}
		
		return condition;
	}
	
	// 1-2. 검색에 대한 문자열을 붙이는 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private int searchSetData(PageObject pageObject, PreparedStatement pstmt, int idx) 
			throws SQLException {
		
		String word = pageObject.getWord();
		
		if(word != null && !word.equals("")) {
			if(pageObject.getKey().indexOf("t") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if(pageObject.getKey().indexOf("c") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if(pageObject.getKey().indexOf("w") != -1)
				pstmt.setString(idx++, "%" + word + "%");
		}
		
		return idx;
	}
	
	// 2. 글보기
	public  ImageVO view(long no) throws Exception {
		// 콘솔에서 확인해야 할 내용.
		ImageVO vo = null;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "select i.no, i.title, i.content, i.id, m.name, "
					+ " to_char(i.writeDate, 'yyyy.mm.dd') writeDate, "
					+ " i.fileName from image i, member m "
					+ " where (no = ?) and (i.id = m.id)";
//			System.out.println("ImageDAO.view().sql - " + sql);
			// 4. 실행객체 && 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 / 데이터 담기
			if(rs != null && rs.next()) {
				// 데이터가 있으면 vo 를 생성한다.
				vo = new ImageVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setFileName(rs.getString("fileName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("이미지 게시판 글보기 - 데이터 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	} // view()의 끝
	
	// 3. 글쓰기 - 처리문 들어 있는 메서드 작성	
	public int write(ImageVO vo)  throws Exception {
		int result = 0;
		
		// JDBC 프로그램 형태
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "insert into image(no, title, content, id, fileName) "
					+ " values(image_seq.nextval, ?, ?, ?, ?)";
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getFileName());
			// 5. 실행 - insert 
			result = pstmt.executeUpdate();
			// 6. 데이터 표시 또는 담기
			System.out.println("이미지 게시판 글등록이 되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("이미지 게시판 글쓰기 - 글쓰기 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	// 4. 글수정
	public int update(ImageVO vo)  throws Exception {
		System.out.println("ImageDAO.update().vo - " + vo);
		int result = 0;
		
		//JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "update image set title = ?, content = ?, writer = ? where no = ?";
			// 4. 실행객체 & 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
//			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과표시 & 데이터 담기
			if(result == 0) System.out.println("수정할 데이터가 존재하지 않습니다.");
			else System.out.println("데이터 수정이 완료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("이미지 게시판 글수정 - 글수정 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
	
	// 5. 글삭제
	public int delete(long no)  throws Exception {
		System.out.println("ImageDAO.delete().no - " + no);
		int result = 0;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			// 3.
			String sql = "delete from image where no = ?";
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
			throw new Exception("이미지 게시판 글삭제 - 글삭제 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
}

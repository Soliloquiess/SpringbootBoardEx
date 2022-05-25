package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;

public class BoardDAO {

	// 전역변수 = 멤버 변수 = 필드
	// 드라이버명
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	// 서버정보
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "java00";
	String upw = "java00";
	
	// 필요한 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 1. 전체 데이터 가져오기
	public long getTotalRow() {
		long cnt = 0;
		
		try { // 정상처리
			// 1. 드라이버 확인-> 프로젝트 내에서 한번만 하시면 됩니다. -> MainController에서 한번만 확인
			// 클래스 유무 확인 -> static 멤버를 로딩해 준다.
			Class.forName(driver);
			System.out.println("BoardDAO.getTotalRow() - 드라이버 확인 완료");
			// 2. 연결
			con = DriverManager.getConnection(url, uid, upw);
			System.out.println("BoardDAO.getTotalRow() - 연결 완료");
			// 3. sql 작성
			String sql = "select count(*) from board";
			System.out.println("BoardDAO.getTotalRow().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			System.out.println("BoardDAO.getTotalRow() - 실행객체 가져오기.");
			// 5. 실행
			// pstmt.executeQuery() - select 문
			// pstmt.executeUpdate() - insert, update, delete 문
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("게시판의 전체 글의 개수 : " + cnt);
			} else System.out.println("데이터가 존재하지 않습니다.");
		}catch (Exception e) { // 예외처리
			e.printStackTrace(); // 오류 출력
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); // 오류 출력
			}
		}
		
		return cnt;
	}
	
	// 1-1.리스트 - MainController - BoardController - BoardListService - BoardDAO
	public List<BoardVO> list(){
		System.out.println("BoardDAO.list()");
		List<BoardVO> list = null;
		
		try {// 정상처리
			// 1. 드라이버 확인
			Class.forName(driver);
			System.out.println("BoardDAO.list() - 드라이버 확인 완료.");
			// 2. 연결
			con = DriverManager.getConnection(url, uid, upw);
			System.out.println("BoardDAO.list() - 연결 완료.");
			// 3. sql 작성 - 페이지 처리된 데이터 1페이지
			String sql = " select no, title, writer, writeDate, hit from board order by no desc ";
			sql = " select rownum rnum, no, title, writer, writeDate, hit from( " + sql + ") ";
			sql = " select rnum, no, title, writer, "
					+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit from (" + sql
					+ ") where rnum between ? and ?";
			System.out.println("BoardDAO.list().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1); // 시작 번호 - 1 :1페이지 정보
			pstmt.setInt(2, 10); // 끝 번호 - 10 : 1페이지 정보
			System.out.println("BoardDAO.list() - 실행 객체 생성 완료.");
			// 5. 실행
			rs = pstmt.executeQuery();
			System.out.println("BoardDAO.list() - 실행 완료.");
			// 6. 표시 & 담기
			// rs -> boardVO(생성->데이터담기) 데이터 있는 만큼 반복처리:while -> list(list가 null이면 생성한다.)
			if(rs != null) {
				// 데이터가 있으면 있는 만큼 반복처리한다.
				while(rs.next()) {
					// list가 null이면 생성한다. 처음 한번만 실행이된다.
					if(list == null) list = new ArrayList<BoardVO>();
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					// list 에 담는다.
					list.add(vo);
				} // while의 끝
			}// if문의 끝
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력
		} finally {
			try {
				// 7. 열려 있는 객체 닫기
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); // 오류 출력
			}
		}
		
		// 가져온 데이터를 확인한다.
		System.out.println("BoardDAO.list().list - " + list);
		
		return list;
	} // list()의 끝-------------------------------
	
	
	// 2. 조회수 1 증가
	public int increase(long no) {
		System.out.println("BoardDAO.increase().no - " + no);
		
		int result = 0;
		
		// JDBC 프로그램
		try {
			// 1. 확인
			Class.forName(driver);
			System.out.println("BoardDAO.increase() - 드라이버 확인 완료");
			// 2. 연결
			con = DriverManager.getConnection(url, uid, upw);
			System.out.println("BoardDAO.increase() - 연결 완료");
			// 3. sql
			String sql = "update board set hit = hit + 1 where no = ?";
			System.out.println("BoardDAO.increase().sql - " + sql);
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			System.out.println("BoardDAO.increase() - 실행 객체와 데이터 셋팅 완료");
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 표시
			System.out.println("BoardDAO.increase() - 조회수 1 증가 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	// 2-1. 글보기
	public  BoardVO view(long no) {
		// 콘솔에서 확인해야 할 내용.
		System.out.println("BoardDAO.view().no - " + no);
		BoardVO vo = null;
		
		// JDBC 프로그램
		try {
			// 1. 확인
			Class.forName(driver);
			System.out.println("BoardDAO.view() - 드라이버 확인 완료.");
			// 2. 연결
			con = DriverManager.getConnection(url, uid, upw);
			System.out.println("BoardDAO.view() - 연결 완료.");
			// 3. sql
			String sql = "select no, title, content, writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
					+ " hit from board where no = ?";
			System.out.println("BoardDAO.view().sql - " + sql);
			// 4. 실행객체 && 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			System.out.println("BoardDAO.view() - 실행객체 생성 완료.");
			// 5. 실행
			rs = pstmt.executeQuery();
			System.out.println("BoardDAO.view().rs - " + rs );
			// 6. 표시 / 데이터 담기
			if(rs != null && rs.next()) {
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
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println("BoardDAO.view().vo - " + vo);
		return vo;
	} // view()의 끝
	
	// 3. 글쓰기 - 처리문 들어 있는 메서드 작성	
	public int write(BoardVO vo) {
		System.out.println("BoardDAO.write().vo - " + vo);
		int result = 0;
		
		// JDBC 프로그램 형태
		try {
			// 1. 확인
			Class.forName(driver);
			System.out.println("BoardDAO.write() - 드라이버 확인 완료");
			// 2. 연결
			con = DriverManager.getConnection(url, uid, upw);
			System.out.println("BoardDAO.write() - 연결 완료");
			// 3. sql
			String sql = "insert into board(no, title, content, writer) "
					+ " values(board_seq.nextval, ?, ?, ?)";
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
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 4. 글수정
	public int update(BoardVO vo) {
		System.out.println("BoardDAO.update().vo - " + vo);
		int result = 0;
		
		return result;
	}
	
	
	// 5. 글삭제
	
	
}

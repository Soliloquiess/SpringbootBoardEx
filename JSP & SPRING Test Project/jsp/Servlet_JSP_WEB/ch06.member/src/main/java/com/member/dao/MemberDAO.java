package com.member.dao;

import com.member.vo.MemberVO;
import com.util.db.DAO;
import com.util.db.DBInfo;
import com.util.io.Out;

public class MemberDAO extends DAO {

	// id 중복 체크 메서드 ---------------------------------------------
	public String idCheck(String id) throws Exception {
		String dbId = null;
		
		try {
			// 1. 확인
			// 2. 연결
			con = DBInfo.getConnection();
			// 3. sql - id를 가져오는 처리문
			String sql = " select id from member where id = ?";
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next())
				dbId = rs.getString("id");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("아이디 중복 체크 중 DB 오류 발생");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return dbId;
	}
	
	// 회원 가입 메서드 : insert 쿼리 pstmt.executeUpdate() ----------------
	public Integer write(MemberVO vo) throws Exception {
		Integer result = null;
		
		try {
			// 1. 확인 2. 연결
			con = DBInfo.getConnection();
			// 3. sql
			String sql = "insert into member(id, pw, name, gender, birth, tel, email, photo) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
			// 4. 실행객체 & 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 담기 | 표시
			Out.title("회원가입 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원가입 중 DB 오류 발생");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	// 회원 정보보기 메서드 : select 쿼리 pstmt. executeQuery() -> rs --------
	public MemberVO view(String id) throws Exception {
		MemberVO vo = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3.
			String sql = "select m.id, m.name, m.gender, m.birth, m.tel, m.email, m.photo, "
					+ " m.regDate, m.conDate, m.status, m.gradeNo, g.gradeName "
					+ " from member m, grade g "
					+ " where (id = ?) and (m.gradeNo = g.gradeNo) ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoto(rs.getString("photo"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보 보기 처리 중 DB 오류 발생");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	}
}

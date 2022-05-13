package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.vo.MemberVO;
import com.member.vo.MemberVO;
import com.util.db.DB;
import com.util.io.Out;

import java.util.ArrayList;
import java.util.List;

import com.member.vo.MemberVO;
import com.util.db.DAO;
import com.util.db.DB;

public class MemberDAO extends DAO {

	// 회원 리스트
//	public List<MemberVO> list() throws Exception{
	public List<MemberVO> list() throws Exception {
//	System.out.println("MemberDAO.list()");
		List<MemberVO> list = null;
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3. sql

			String sql = "select m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate from member m, grade g where m.gradeNo = g.gradeNo order by m.id";
//			sql = "select rownum rnum,  id, name, gender, birth, tel, gradeNo, gradeName, status from( "
//					+ sql + ")";
//			sql = "select rnum,  id, name, gender, birth, tel, gradeNo, gradeName, status from( "
//					+ sql + ") where rnum between ? and ? ";
			// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);

			// pstmt.setInt(1, 1);
//			pstmt.setLong(1,no);
//			pstmt.setInt(2, 10);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if (rs != null) {

				while (rs.next()) {
					if (list == null)
						list = new ArrayList<MemberVO>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setConDate(rs.getString("conDate"));

					list.add(vo);
				}

			} // if의 끝

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 리스트 DB 처리 오류");
		} finally {
			DB.close(con, pstmt, rs);
		}

		return list;
	}

	// 보여주기

	public MemberVO view(String id) throws Exception {
		MemberVO vo = null;

		// 예외처리
		try {
			// 1.2.
			con = DB.getConnection();
			String sql = "select m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, m.conDate, m.status, m.gradeNo, g.gradeName from member m , grade g where (id=?) and (m.gradeNo=g.gradeNo)";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 5.실행
			rs = pstmt.executeQuery();
			// 6.
			if (rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 넣기 DB 처리 오류");
		} finally {
			DB.close(con, pstmt, rs);
		}
		return vo;
	}

	// 회원 가입
	public int write(MemberVO vo) throws Exception {
		// System.out.println("MemberDAO.write().vo : " + vo);
		int result = 0;

		try {
			// 1. 2.
			con = DB.getConnection();
			// 3. sql
			String sql = " insert into member(id, pw, name, gender, birth, tel, email, photo) "
					+ " values(?, ?, ?, ? , ?, ?, ?, ?) ";
			// 4. 실행객체 & 데이터 셋팅
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
			// 6. 데이터 저장
			Out.title("회원가입이 되셨습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 가입 DB 처리 오류");
		} finally {
			DB.close(con, pstmt);
		}

		return result;
	}

	// 회원 가입
	public int update(MemberVO vo) throws Exception {
		// System.out.println("MemberDAO.write().vo : " + vo);
		int result = 0;

		try {
			// 1. 2.
			con = DB.getConnection();
			// 3. sql
			String sql ="update member set name = ?, gender=?, birth=?, tel=?, email=? where id=? and pw=?";		// 4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getId());
			pstmt.setString(7, vo.getPw());

			// 5. 실행
			
			//result =1 :수정 완료 0: 아이디나 비밀번호가 틀림
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			if (result == 1) {
				System.out.println("MemberDAO.update()- 회원 수정 완료");

			} else {
				System.out.println("memberdao.update()-회원 수정 실패, 아이디나 비밀번호 틀림");

			//	throw new Exception("다시 입력"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
 
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public int delete(MemberVO vo) throws Exception {
		// System.out.println("MemberDAO.write().vo : " + vo);
		int result = 0;

		try {
			// 1. 2.
			con = DB.getConnection();
			// 3. sql
			String sql = "delete from member where id = ? and pw = ? and tel =?";
			// 4. 실행객체 & 데이터 셋팅
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getTel());
				
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			if (result == 1) {
				System.out.println("회원 탈퇴 성공");

			} else {
				System.out.println("삭제 오류 "+ vo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}

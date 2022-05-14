package com.webjjang.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.db.DB;

public class MemberDAO {

	// 필요한 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<MemberVO> list() throws Exception{
		List<MemberVO> list = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "SELECT m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate "
					+ " FROM member m, grade g "
					+ " WHERE m.gradeNo = g.gradeNo "
					+ " ORDER BY m.id ";
			//4 
			pstmt = con.prepareStatement(sql);
			// 5
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<MemberVO>();
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

	public MemberVO view(String id) throws Exception{
		// TODO Auto-generated method stub
		MemberVO vo = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "SELECT m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, "
					+ " m.conDate, m.status, m.gradeNo, g.gradeName "
					+ " FROM member m, grade g "
					+ " WHERE (id = ?) AND (m.gradeNo = g.gradeNo) ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			//5. 실행
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
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
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

	public int write(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			// 1.2.
			con = DB.getConnection();
			// 3.
			String sql = "INSERT INTO member(id, pw, name, gender, birth, tel, email, photo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			//5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("회원가입 완료");
			
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

	public int update(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			//1.2
			con = DB.getConnection();
			// 3.
			String sql = "UPDATE member set name = ?, gender = ?, "
					+ " birth = ?, tel= ?, email = ? "
					+ " WHERE id = ? AND pw = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getId());
			pstmt.setString(7, vo.getPw());
			// 5.
			//  result : 1 - 수정 완료, result : 0 - 아이디나 비밀번호가 틀림.
			result = pstmt.executeUpdate();
			// 6
			if(result == 1)
				System.out.println("MemberDAO.update() - 회원 정보 수정 완료");
			else {
				System.out.println("MemberDAO.update() - 회원 정보 수정 실패 : 아이디나 비밀번호 틀림.");
				// throw new Exception("아이디나 비밀번호를 확인해 주세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e);
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

	public int delete(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "DELETE FROM member "
					+ " WHERE id = ? AND pw = ? AND tel = ?";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getTel());
			//5
			result = pstmt.executeUpdate();
			// 6
			if(result == 1) System.out.println("회원 탈퇴/삭제 성공");
			else System.out.println("삭제 오류 - 글번호 없음. id = " + vo.getId());
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
			String sql = "update member set hit = hit + 1 where no = ?";
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
	
	public LoginVO login(LoginVO inVO) throws Exception{
		// TODO Auto-generated method stub
		LoginVO vo = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "SELECT m.id, m.name, m.gradeNo, g.gradeName, m.photo "
					+ " FROM member m, grade g "
					+ " WHERE (id = ? AND pw = ?) AND (m.gradeNo = g.gradeNo) ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inVO.getId());
			pstmt.setString(2, inVO.getPw());
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null && rs.next()) {
				vo = new LoginVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setPhoto(rs.getString("photo"));
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

} // end of MemberDAO class

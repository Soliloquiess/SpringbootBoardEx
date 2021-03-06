package com.webjjang.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.db.DAO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.io.Out;

public class MemberDAO extends DAO {

	// 회원 리스트
	public List<MemberVO> list() throws Exception{
		System.out.println("MemberDAO.list()");
		List<MemberVO> list = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " select m.id, m.name, m.gender, m.birth, m.tel, m.gradeNo, g.gradeName, m.status "
					+ " from member m, grade g "
					+ " where g.gradeNo = m.gradeNo"
					+ " order by id asc ";
			sql = "select rownum rnum,  id, name, gender, birth, tel, gradeNo, gradeName, status from( "
					+ sql + ")";
			sql = "select rnum,  id, name, gender, birth, tel, gradeNo, gradeName, status from( "
					+ sql + ") where rnum between ? and ? ";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 10);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if(rs != null) {
				
				while(rs.next()) {
					if(list == null) list = new ArrayList<MemberVO>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
				}
				
			} // if의 끝
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 리스트 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}

	
	// 회원 가입
	public Integer write(MemberVO vo) throws Exception{
		System.out.println("MemberDAO.write().vo : " + vo);
		Integer result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " insert into member(id, pw, name, gender, birth, tel, email, photo) "
					+ " values(?, ?, ?, ? , ?, ?, ?, ?) ";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			Out.title("회원가입이 되셨습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 가입 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	
}

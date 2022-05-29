package com.member.dao;

import com.member.vo.LoginVO;
import com.util.db.DAO;
import com.util.db.DBInfo;

public class MemberDAO extends DAO {

	public LoginVO login(LoginVO vo) throws Exception{
		LoginVO loginVO = null;
		
		try {
			// 1. 2. DBInfo
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " select m.id, m.name, m.gradeNo, g.gradeName "
					+ " from member m, grade g "
					+ " where (m.id = ? and m.pw = ?) and (m.gradeNo = g.gradeNo)";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			//5. 실행
			rs = pstmt.executeQuery();
			//6. 표시 및 저장
			if(rs != null && rs.next()) {
				loginVO = new LoginVO();
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 처리 중 DB 오류");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// loginVO == null - 정보가 정확하지 않다.
		
		return loginVO;
	}
	
}

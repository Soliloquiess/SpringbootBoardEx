package com.member.dao;

import com.util.db.DAO;
import com.util.db.DBInfo;

public class MemberDAO extends DAO {
	//id 중복 체크 메서드
	public String idCheck(String id) throws Exception {
		String dbId = null;
		
		try {
			
			//1.확인
			//2.연결
			con = DBInfo.getConnection();
			//3.sql
			String sql = "select id from member where id = ?";
			//4.실행객체& 데이터 세팅
			pstmt = con.prepareStatement(sql);//처리할 준비할 객체
			pstmt.setString(1, id);	//STring에 해당되는 id집어넣어서 비교해보자
			
			//5. 실행
			rs = pstmt.executeQuery();
			//6.데이터 표시 또는 저장
			if(rs!=null && rs.next()) {
				dbId = rs.getString("id");
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("아이디 중복 체크 중 DB오류 발생");
		}finally {
			//7.닫기
			DBInfo.close(con, pstmt, rs);
		}
		return dbId;
	}
}

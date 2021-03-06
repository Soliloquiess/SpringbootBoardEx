package com.webjjang.message.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.db.DAO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.io.Out;

public class MessageDAO extends DAO{

	public List<MessageVO> list(String id) throws Exception{
		System.out.println("MessageDAO.list()");
		List<MessageVO> list = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " select m.no, m.sender, sm.name senderName, m.sendDate, "
					+ " m.accepter, am.name accepterName, m.acceptDate "
					+ " from message m, member sm, member am  "
					+ " where (m.sender = ? or m.accepter = ? or m.allUser = 1) "
					+ " and (m.sender = sm.id and m.accepter = am.id) "
					+ " order by no desc ";
			sql = "select rownum rnum, no, sender, senderName, sendDate, accepter, accepterName, acceptDate from( "
					+ sql + ")";
			sql = "select rnum, no, sender, senderName, to_char(sendDate, 'yyyy.mm.dd') sendDate, "
					+ " accepter, accepterName, to_char(acceptDate, 'yyyy.mm.dd') acceptDate from( "
					+ sql + ") where rnum between ? and ? ";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setLong(3, 1);
			pstmt.setLong(4, 10);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if(rs != null) {
				
				while(rs.next()) {
					if(list == null) list = new ArrayList<MessageVO>();
					MessageVO vo = new MessageVO();
					vo.setNo(rs.getLong("no"));
					vo.setSender(rs.getString("sender"));
					vo.setSenderName(rs.getString("senderName"));
					vo.setSendDate(rs.getString("sendDate"));
					vo.setAccepter(rs.getString("accepter"));
					vo.setAccepterName(rs.getString("accepterName"));
					vo.setAcceptDate(rs.getString("acceptDate"));
					
					list.add(vo);
				}
				
			} // if의 끝
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 게시판 리스트 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public Integer write(MessageVO vo) throws Exception{
		System.out.println("MessageDAO.write()");
		Integer result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " insert into message(no, content, sender, accepter) values(message_seq.nextval, ?, ?, ?)";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getSender());
			pstmt.setString(3, vo.getAccepter());
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			Out.titleWithLine("메시지가 보내졌습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 보내기 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
}

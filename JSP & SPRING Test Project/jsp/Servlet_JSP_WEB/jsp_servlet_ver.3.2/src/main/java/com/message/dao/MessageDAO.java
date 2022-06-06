package com.message.dao;

import java.util.ArrayList;
import java.util.List;

import com.message.vo.MessageVO;
import com.util.db.DAO;
import com.util.db.DBInfo;
import com.util.io.Out;

public class MessageDAO extends DAO{

	// 메시지 리스트 가져오기
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

	// 메시지 보기 - 먼저 읽음 표시를 한다. - 읽은 날짜를 오늘 날짜로 셋팅한다.
	public MessageVO view(Long no) throws Exception{
		System.out.println("MessageDAO.view().no : " + no);
		MessageVO vo = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " select m.no, m.content, m.sender, sm.name senderName, m.sendDate, "
					+ " m.accepter, am.name accepterName, m.acceptDate "
					+ " from message m, member sm, member am  "
					+ " where (m.no = ?) "
					+ " and (m.sender = sm.id and m.accepter = am.id) " ;
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if(rs != null && rs.next()) {
				
				vo = new MessageVO();
				vo.setNo(rs.getLong("no"));
				vo.setContent(rs.getString("content"));
				vo.setSender(rs.getString("sender"));
				vo.setSenderName(rs.getString("senderName"));
				vo.setSendDate(rs.getString("sendDate"));
				vo.setAccepter(rs.getString("accepter"));
				vo.setAccepterName(rs.getString("accepterName"));
				vo.setAcceptDate(rs.getString("acceptDate"));
				
			} // if의 끝
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 보기 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	}
	
	// 메시지 읽음 표시 - 메시지 보기를 다음에 한다.- 읽은 날짜를 현재 날짜로 바꾼다.
	public Integer setReaded(Long no) throws Exception{
		System.out.println("MessageDAO.setReaded().no : " + no);
		Integer result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " update message set acceptDate = sysdate where no = ? and acceptDate is null ";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			if(result == 1) // result == 1 : 변경이 일어 났다. 0: 변경이 일어 나지 않았다.
				Out.titleWithLine("메시지가 읽음 표시가 되었습니다..");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 읽음 표시 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	// 메시지 쓰기(보내기)
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
	
	// 메시지 삭제
	public Integer delete(MessageVO vo) throws Exception{
		System.out.println("MessageDAO.delete().vo : " + vo);
		Integer result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " delete from message "
					+ " where no = ? and (accepter = ? or (sender = ? and acceptDate is null)) ";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			// 2. accepter -> 내메시지에 대한 처리만 가능하기 때문에 내 아이디를 한 곳에만 저장해서 넘긴다. - sender에 저장하는 것으로 한다.
			pstmt.setString(2, vo.getSender());
			pstmt.setString(3, vo.getSender());
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			if(result == 1)
				Out.titleWithLine("메시지가 삭제되었습니다.");
			else 
				Out.titleWithLine("보낸 메시지를 상대편이 이미 읽었기 때문에 삭제할 수 없습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 삭제 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
}

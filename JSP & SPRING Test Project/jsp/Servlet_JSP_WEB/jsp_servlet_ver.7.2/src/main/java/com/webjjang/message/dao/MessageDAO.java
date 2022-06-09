package com.webjjang.message.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DAO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.io.Out;

public class MessageDAO extends DAO{

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
			String sql = "select count(*) from message " + getCondition(pageObject, "");
			System.out.println("MessageDAO.getTotalRow().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, pageObject.getAccepter());
			if(pageObject.getAcceptMode() == 3)
				pstmt.setString(idx++, pageObject.getAccepter());
			
			// 5. 실행
			// pstmt.executeQuery() - select 문 --> ResultSet
			// pstmt.executeUpdate() - insert, update, delete 문 --> int
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("메시지의 전체 글의 개수 : " + cnt);
			} else System.out.println("메시지의 데이터가 존재하지 않습니다.");
		}catch (Exception e) { // 예외처리
			e.printStackTrace(); // 오류 출력 - 개발자를 위한 오류 출력
			// 메서드 선언한 부분 뒤에 throws 에 추가하거나 catch를 더 잡아 주면 된다.
			throw new Exception("500::메시지 전체 개수 가져오기 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		} // try~catch~finally의 끝
		
		return cnt;
	}
	

	
	// 메시지 리스트 가져오기
	public List<MessageVO> list(PageObject pageObject) throws Exception{
		System.out.println("MessageDAO.list()");
		List<MessageVO> list = null;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			// 보여 줄 메시지 모드 : 1 - 받은 메시지, 2 - 보낸 메시지, 3 - 모든 메시지(기본),
			//       4 - 새로운 메시지(받은 메시지 중에 읽지 않은 메시지)
			// sm = sender message/ am = accepter message/ m = message
			String sql = " select m.no, m.sender, sm.name senderName, m.sendDate, "
					+ " m.accepter, am.name accepterName, m.acceptDate "
					+ " from message m, member sm, member am  "
					+ getCondition(pageObject, "m.")
					+ " and (m.sender = sm.id and m.accepter = am.id) "
					+ " order by no desc ";
			sql = "select rownum rnum, no, sender, senderName, sendDate, "
					+ " accepter, accepterName, acceptDate from( " + sql + ")";
			sql = "select rnum, no, sender, senderName, to_char(sendDate, 'yyyy.mm.dd') sendDate, "
					+ " accepter, accepterName, to_char(acceptDate, 'yyyy.mm.dd') acceptDate from( "
					+ sql + ") where rnum between ? and ? ";
			System.out.println("MessageDAO.list().sql : " + sql);
			//4. 실행객체 & 데이터 셋팅
			int idx = 1;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(idx++, pageObject.getAccepter());
			if(pageObject.getAcceptMode() == 3)	//모든 메시지
				pstmt.setString(idx++, pageObject.getAccepter());
			pstmt.setLong(idx++, 1);// 변수로 써야되는 이유는 조건문이 여러개 변수 올 수 있으므로(idx가 1이될수도, 2가될수도 있으므로
			pstmt.setLong(idx++, 10);
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
			throw new Exception("500::메시지 게시판 리스트 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}

	// 메시지 조건 처리를 위한 메서드
	public String getCondition(PageObject pageObject, String alias) {
		String condition = "";

		if(pageObject.getAcceptMode() == 1) {	//메시지가 1이면(받은 사람만)
			condition += " where (" + alias + "accepter = ? or " + alias + "allUser = 1) ";
		} else if(pageObject.getAcceptMode() == 4) {	//4- 새로운 메시지 .  받지 않은 메시지 중에 읽지 않은 메시지
			condition += " where ((" + alias + "accepter = ? or " + alias + "allUser = 1) and acceptDate is null ) ";
		} else if(pageObject.getAcceptMode() == 2) {////메시지가 2이면(보낸 사람만)
			condition += " where (" + alias + "sender = ? or " + alias + "allUser = 1) ";
		} else { // mode = 3이면 모든 메시지만
			condition += " where (" + alias + "sender = ? or " + alias + "accepter = ? or " + alias + "allUser = 1) ";
		}

		return condition;
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
			throw new Exception("500::메시지 보기 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	}
	
	// 메시지 읽음 표시 - 메시지 보기를 다음에 한다.- 읽은 날짜를 현재 날짜로 바꾼다.
	public Integer setReaded(MessageVO vo) throws Exception{
		System.out.println("MessageDAO.setReaded().vo : " + vo);
		Integer result = 0;
		
		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. sql
			String sql = " update message set acceptDate = sysdate "
					+ " where no = ? and acceptDate is null and accepter = ?";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getAccepter());
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장
			if(result == 1) // result == 1 : 변경이 일어 났다. 0: 변경이 일어 나지 않았다.
				Out.titleWithLine("메시지가 읽음 표시가 되었습니다..");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("500::메시지 읽음 표시 DB 처리 오류");
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
			String sql = " insert into message(no, content, sender, accepter) "
					+ " values(message_seq.nextval, ?, ?, ?)";
			//4. 실행객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getSender());
			pstmt.setString(3, vo.getAccepter());
			//5. 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 저장 - 로고로 출력
			Out.titleWithLine("메시지가 보내졌습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("500::메시지 보내기 DB 처리 오류");
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

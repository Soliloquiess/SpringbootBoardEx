package com.board.service;
/*
 * 게시판 글보기
 * 
 * public BoardVO service()
 */

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.Service;

public class BoardViewService implements Service{
	
	
	private BoardDAO dao;
	
	
	//dao를 넣어주는 setter(서버가 실행되면 바로 넣어준다.-->Init.init)
	public void setDao(Object dao) {
		this.dao = (BoardDAO)dao;
	}
	
	//	public void setDao(BoardDAO dao) {
//		this.dao = dao;
//	}


	@Override
	public BoardVO service(Object obj) throws Exception{
		// DB에서 글번호에 맞는 데이터 가져오기 -> BoardDAO. view(long no)
		
		//DB에서 글 번호에 맞는 데이터 가져오기 -> BoardDAO.view (Object[]{long no, int inc})
		
		Object[] objs = (Object[]) obj;
		Long no = (Long)objs[0];
		int inc = (Integer) objs[1];
		
		
		// 객체 생성
//		BoardDAO dao = new BoardDAO();
		
		// 메서드 호출 조회수 1 증가
		
		if(inc==1) dao.increase((long) no);
		
		return dao.view((long) no);
	}
}

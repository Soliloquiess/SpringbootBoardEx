package com.board.service;
/*
 * 게시판 글보기
 * 
 * public BoardVO service()
 */

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.ServiceInterface;

public class BoardViewService implements ServiceInterface{

	@Override
	public BoardVO service(Object no) throws Exception{
		// DB에서 글번호에 맞는 데이터 가져오기 -> BoardDAO. view(long no)
		// 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 메서드 호출 조회수 1 증가
		dao.increase((long) no);
		
		return dao.view((long) no);
	}
}

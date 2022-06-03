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


	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// 객체 선언
	private BoardDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (BoardDAO) dao;
	}


	@Override
	public BoardVO service(Object obj) throws Exception{
		// DB에서 글번호에 맞는 데이터 가져오기 -> BoardDAO. view(Object[]{long no, int inc})
		Object[] objs = (Object[]) obj; 
		Long no = (Long)objs[0];
		int inc = (Integer)objs[1];
		
		// dao 확인
		System.out.println("BoardViewService.service().dao : " + dao);
		
		// inc == 1 이면 메서드 호출 조회수 1 증가
		if(inc == 1) dao.increase((long) no);
		
		return dao.view((long) no);
	}
}

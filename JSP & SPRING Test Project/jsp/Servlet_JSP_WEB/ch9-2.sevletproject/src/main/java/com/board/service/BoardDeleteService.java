package com.board.service;

import com.board.dao.BoardDAO;
import com.main.controller.Service;

public class BoardDeleteService implements Service{

	//DAO를 연결하여 실행하기 위해서 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.

	// 객체 생성
	
	private BoardDAO dao;
	
	
	//dao를 넣어주는 setter(서버가 실행되면 바로 넣어준다.-->Init.init)
	public void setDao(Object dao) {
		this.dao = (BoardDAO)dao;
	}
	
	
	//	public void setDao(BoardDAO dao) {
//		this.dao = dao;
//	}

	
	@Override
	public Integer service(Object no) throws Exception {
		return new BoardDAO().delete((long) no);
	}
	
}

package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.Service;

public class BoardWriteService implements Service {	
	
	private BoardDAO dao;	
	
	//dao를 넣어주는 setter(서버가 실행되면 바로 넣어준다.-->Init.init)
	public void setDao(Object dao) {
		this.dao = (BoardDAO)dao;
	}
	
	//	public void setDao(BoardDAO dao) {
//		this.dao = dao;
//	}

	@Override
	// BoardController -> BoardWriteService
	public Integer service(Object vo) throws Exception {
		
		return dao.write((BoardVO) vo);
	}

}

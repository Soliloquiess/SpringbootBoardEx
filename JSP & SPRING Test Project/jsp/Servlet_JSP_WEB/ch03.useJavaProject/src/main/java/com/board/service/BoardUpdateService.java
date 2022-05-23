package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.ServiceInterface;

public class BoardUpdateService implements ServiceInterface {

	@Override
	public Integer service(Object vo) throws Exception {
		
		return new BoardDAO().update((BoardVO) vo);
	}
	
}

package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.ServiceInterface;

public class BoardWriteService implements ServiceInterface {
	
	@Override
	// BoardController -> BoardWriteService
	public Integer service(Object vo) throws Exception {
		
		return new BoardDAO().write((BoardVO) vo);
	}

}

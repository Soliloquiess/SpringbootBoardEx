package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.ServiceInterface;

public class BoardWriteService implements ServiceInterface {
	
	@Override
	// BoardController -> BoardWriteService
	public Integer service(Object vo) throws Exception {
		
		return new BoardDAO().write((BoardVO) vo);
	}

}

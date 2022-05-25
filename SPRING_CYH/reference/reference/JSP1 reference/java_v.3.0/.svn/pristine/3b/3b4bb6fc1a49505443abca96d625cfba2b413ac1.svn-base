package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.ServiceInterface;

public class BoardUpdateService implements ServiceInterface {

	@Override
	public Integer service(Object vo) throws Exception {
		
		return new BoardDAO().update((BoardVO) vo);
	}
	
}

package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.ServiceInterface;

public class BoardDeleteService implements ServiceInterface{

	@Override
	public Integer service(Object no) throws Exception {
		return new BoardDAO().delete((long) no);
	}
	
}

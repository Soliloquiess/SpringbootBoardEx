package com.board.service;

import com.board.dao.BoardDAO;
import com.main.controller.ServiceInterface;

public class BoardDeleteService implements ServiceInterface{

	@Override
	public Integer service(Object no) throws Exception {
		return new BoardDAO().delete((long) no);
	}
	
}

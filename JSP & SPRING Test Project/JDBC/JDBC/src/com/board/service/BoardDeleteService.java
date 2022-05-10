package com.board.service;

import com.board.dao.BoardDAO;

public class BoardDeleteService {
	public int service(long no) throws Exception{
		//DB처리를 한다. 생성하고 호출
		BoardDAO dao = new BoardDAO();
		return dao.delete(no);
	}
}

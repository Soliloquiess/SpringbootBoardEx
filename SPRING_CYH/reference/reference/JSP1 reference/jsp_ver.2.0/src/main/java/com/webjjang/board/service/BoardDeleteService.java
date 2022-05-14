package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;

public class BoardDeleteService {

	public int service(long no) throws Exception {
		// 생성하고 호출
		BoardDAO dao = new BoardDAO();
		return dao.delete(no);
	}
	
}

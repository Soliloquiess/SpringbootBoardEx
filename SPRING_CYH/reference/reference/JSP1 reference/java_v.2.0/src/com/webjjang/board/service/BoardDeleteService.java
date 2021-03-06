package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;

public class BoardDeleteService {

	public int service(long no) throws Exception {
		System.out.println("BoardDeleteService.service().no - " + no);
		int result = 0;
		
		// DB에 데이터를 삭제 처리 -> BoardDAO.delete(no)
		BoardDAO dao = new BoardDAO();
		result = dao.delete(no);
		
		return result;
	}
	
}

package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;

public class BoardUpdateService {

	public int service(BoardVO vo) throws Exception {
		int result = 0;
		
		// update 처리 -> BoardDAO.update(BoardVO vo)
		BoardDAO dao = new BoardDAO();
		result = dao.update(vo);
		
		return result;
	}
	
}

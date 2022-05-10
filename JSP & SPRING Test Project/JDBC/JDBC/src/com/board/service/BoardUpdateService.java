package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardUpdateService {
	public int service(BoardVO vo ) throws Exception{
		//생성하고 호출
		BoardDAO dao = new BoardDAO();
		
		return dao.update(vo);
	}
}

package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;

public class BoardUpdateService {

	public int service(BoardVO vo) throws Exception{
		// 생성 - 호출
		BoardDAO dao = new BoardDAO();
		return dao.update(vo);
	}
}

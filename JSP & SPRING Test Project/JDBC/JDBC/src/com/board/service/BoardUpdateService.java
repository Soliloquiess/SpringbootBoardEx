package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardUpdateService {
	public int service(BoardVO vo ) throws Exception{
		//�����ϰ� ȣ��
		BoardDAO dao = new BoardDAO();
		
		return dao.update(vo);
	}
}

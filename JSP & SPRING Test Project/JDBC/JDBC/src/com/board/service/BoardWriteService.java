package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardWriteService {
	public void service(BoardVO vo) throws Exception {
		//DAO ���� ȣ��
		BoardDAO dao = new BoardDAO();
		dao.write(vo);
		
	}
}

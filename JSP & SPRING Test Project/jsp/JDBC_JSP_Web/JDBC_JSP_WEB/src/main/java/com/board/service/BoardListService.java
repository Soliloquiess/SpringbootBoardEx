package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardListService {
	public List<BoardVO> service() throws Exception{
		//����ȣ��
		BoardDAO  dao = new BoardDAO();
		return dao.list();
	}
}

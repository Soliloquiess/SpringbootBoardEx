package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardViewService {
	public BoardVO service(long no) throws Exception{
		//생성 호출: controller - [service] - DAO
		BoardDAO dao = new BoardDAO();
		return dao.view(no); 
	}
}

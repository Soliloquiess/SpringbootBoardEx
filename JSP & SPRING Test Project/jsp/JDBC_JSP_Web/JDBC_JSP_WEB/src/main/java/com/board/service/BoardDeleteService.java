package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardDeleteService {
	public int service(long no) throws Exception{
		//생성호출
		BoardDAO  dao = new BoardDAO();
		return dao.delete(no);
	}
}

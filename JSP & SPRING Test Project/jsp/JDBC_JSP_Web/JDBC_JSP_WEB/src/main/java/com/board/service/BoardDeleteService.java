package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardDeleteService {
	public int service(long no) throws Exception{
		//����ȣ��
		BoardDAO  dao = new BoardDAO();
		return dao.delete(no);
	}
}

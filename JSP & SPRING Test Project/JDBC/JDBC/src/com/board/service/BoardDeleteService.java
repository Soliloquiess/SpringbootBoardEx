package com.board.service;

import com.board.dao.BoardDAO;

public class BoardDeleteService {
	public int service(long no) throws Exception{
		//DBó���� �Ѵ�. �����ϰ� ȣ��
		BoardDAO dao = new BoardDAO();
		return dao.delete(no);
	}
}

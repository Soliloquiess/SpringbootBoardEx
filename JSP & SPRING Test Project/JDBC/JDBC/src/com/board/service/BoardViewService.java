package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardViewService {
	public BoardVO service(long no) throws Exception {	//controller
		//��ȸ�� 1 ����
		//��ü�� �����ϰ� ȣ���ؼ� �����͸� �����ͼ� �����Ѵ�.
		BoardDAO dao = new BoardDAO();
		return dao.view(no);
	}
}

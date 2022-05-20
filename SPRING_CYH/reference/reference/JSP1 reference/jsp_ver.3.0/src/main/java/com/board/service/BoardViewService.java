package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;

public class BoardViewService {

	public BoardVO service(long no, long inc) throws Exception {
		// 생성 호출 :Controller - [Service] - DAO
		BoardDAO dao = new BoardDAO();
		// list - view 일 때만 조회수 1증가 시킨다.
		// update와 update 끝나고 view로 갈때 조회수 1증가 시키지 않다.
		if(inc == 1) dao.increase(no);
		return dao.view(no);
	}
	
}

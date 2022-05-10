package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardViewService {
	public BoardVO service(long no) throws Exception {	//controller
		//조회수 1 증가
		//객체를 생성하고 호출해서 데이터를 가져와서 리턴한다.
		BoardDAO dao = new BoardDAO();
		return dao.view(no);
	}
}

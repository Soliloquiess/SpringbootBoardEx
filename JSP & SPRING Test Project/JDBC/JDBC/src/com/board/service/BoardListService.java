package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardListService {
	public List<BoardVO> service(){
		//생성하고 호출한 결과를 리턴한다 - Controller - service -DAO
		BoardDAO dao = new BoardDAO();
		return dao.list(); //List<BoardVO>에서 처리되는 타입과 dao.list에서 처리되는 타입은 동일함.
	}
}

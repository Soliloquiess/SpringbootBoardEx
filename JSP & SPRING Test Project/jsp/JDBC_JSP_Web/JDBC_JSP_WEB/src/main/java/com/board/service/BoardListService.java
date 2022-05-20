package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.util.PageObject;

public class BoardListService {

	public List<BoardVO> service(PageObject pageObject) throws Exception{
		BoardDAO dao = new BoardDAO();
		// 페이지의 정보를 계산하는 처리문이 담겨 있는 메서드를 호출해서 전체 데이터 갯수를 넣는다.
		// 전체 데이터를 세는 처리를 하지 않으면 데이터를 가져오지 않는다. startRow = 0, endRow = 0로 존재하기 때문에
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
}

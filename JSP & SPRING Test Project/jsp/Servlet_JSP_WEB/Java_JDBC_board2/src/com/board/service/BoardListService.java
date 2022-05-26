
// * 게시판 리스트를 해결하기 위한 객체
// * - BoardDAO 객체를 사용해서 DB에서 데이터를 수집해 온다.

package com.board.service;

import com.board.dao.BoardDAO;
import com.main.controller.Service;

public class BoardListService implements Service {
	//얘를 쓰기 위해 BoardDAO가 필요.
	BoardDAO dao = new BoardDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

}


// * 게시판 글삭제를 해결하기 위한 객체
// * - 전달받는 글번호(no)에 맞는 데이터를 DB에서 삭제한다.

package com.board.service;

import com.board.dao.BoardDAO;
import com.main.controller.Service;

public class BoardDeleteService implements Service {
	
	BoardDAO dao = new BoardDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글삭제 - no 데이터를 넘겨주고 DB에서 글삭제를 시켜 준다.
		return dao.delete((Long) obj);
	}

}

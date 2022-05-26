/*
 * 게시판 DB 수정을 해결하기 위한 객체
 * - BoardDAO 객체를 사용해서 DB에서 데이터를 수집해 온다.
 */
package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Service;

public class BoardUpdateService implements Service {
	
	BoardDAO dao = new BoardDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글수정 - vo 데이터를 넘겨주고 DB에 저장시켜 준다.
		return dao.update((BoardVO) obj);
	}

}

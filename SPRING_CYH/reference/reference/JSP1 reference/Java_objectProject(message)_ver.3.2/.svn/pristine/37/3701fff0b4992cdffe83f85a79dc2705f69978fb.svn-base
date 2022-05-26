package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;

public class BoardWriteService {
	
	// BoardController -> BoardWriteService
	public int service(BoardVO vo) {
		System.out.println("BoardWriteService.service().vo - " + vo);
		int result = 0;
		
		// DB사용 - BoardDAO가 필요하다. - 생성하고 호출한다
		// 생성
		BoardDAO dao = new BoardDAO();
		// 메서드 호출
		result = dao.write(vo);
		
		return result;
	}

}

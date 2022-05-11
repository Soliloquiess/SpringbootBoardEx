package com.board.service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardViewService {
	public BoardVO service(long no,long inc) throws Exception{
		//생성 호출: controller - [service] - DAO
		
		BoardDAO dao = new BoardDAO();
		//list - view일때만 조회수 1 증가시킨다.
		//update와 update끝나고 view로 갈 때 조회수 1 증가 시키지 않는다.
		if(inc ==1) dao.increase(no);
		return dao.view(no); 
	}
}

package com.webjjang.image.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.Service;

public class ImageDeleteService implements Service{


	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// 객체 선언
	private BoardDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (BoardDAO) dao;
	}


	@Override
	public Integer service(Object no) throws Exception {
		return dao.delete((long) no);
	}
	
}

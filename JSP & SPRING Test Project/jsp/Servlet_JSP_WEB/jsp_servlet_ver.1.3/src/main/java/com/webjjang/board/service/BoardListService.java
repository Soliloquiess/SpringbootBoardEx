package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Service;

public class BoardListService implements Service{

	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// 객체 선언
	private BoardDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (BoardDAO) dao;
	}



	@Override
	// 예외가 발생이 되면 Controller에 넘긴다.  throws Exception
	// 파라메터 obj가 없으면 null을 넣어주면 된다.
	public List<BoardVO> service(Object obj) throws Exception{
		
		
		// 전체 데이터 구하기 - 오라클에서 구해야한다. DAO . getTotalRow() - long
		long cnt = dao.getTotalRow();
		// 전체 페이지 구하기 / 표시할 링크 페이지 시작과 끝 계산하기. -> 차후에
		// 리스트 데이터 구하기 - 오라클에서 구해야 한다. DAO .list() - List<BoardVO>
//		list = dao.list();
		
//		return list;
		return  dao.list();
	}
	
}

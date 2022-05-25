package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;

public class BoardListService {

	public List<BoardVO> service(){
		System.out.println("BoardListService.service()");
		
		List<BoardVO> list = null;
		
		//DAO를 연결하여 실행하기 위해서 객체 생성하고 메서드 호출한다.
		// 객체 생성
		BoardDAO dao = new BoardDAO();
		// 전체 데이터 구하기 - 오라클에서 구해야한다. DAO . getTotalRow() - long
		long cnt = dao.getTotalRow();
		// 전체 페이지 구하기 / 표시할 링크 페이지 시작과 끝 계산하기. -> 차후에
		// 리스트 데이터 구하기 - 오라클에서 구해야 한다. DAO .list() - List<BoardVO>
		list = dao.list();
		
		return list;
	}
	
}

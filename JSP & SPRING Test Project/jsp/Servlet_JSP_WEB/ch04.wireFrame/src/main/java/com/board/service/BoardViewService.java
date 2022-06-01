package com.board.service;
/*
 * 게시판 글보기
 * 
 * public BoardVO service()
 */

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.main.controller.ServiceInterface;

public class BoardViewService implements ServiceInterface {

	@Override
//	public BoardVO service(Object no) throws Exception{

	//// DB에서 글번호에 맞는 데이터 가져오기 -> BoardDAO. view(long no)

	public BoardVO service(Object obj) throws Exception {
		// DB에서 글 번호에 맞는 데이터 갖오기 - BoardDAO, view(Objects[] {long no, int inc})
		Object[] objs = (Object[]) obj;
//글 번호 보기에서 여러개 보려면 Object형태 객체 안 의 전체 가져옴	

		// DB에서 글번호에 맞는 데이터 가져오기 -> BoardDAO. view(Objects[]{long no, int inc});
		Long no = (Long) objs[0];
		int inc = (Integer) objs[1];
		
		// 객체 생성
		BoardDAO dao = new BoardDAO();

		// inc ==1이면 메서드 호출 조회수 1 증가
		if (inc == 1) {
			dao.increase((long) no);
		}
		return dao.view((long) no);
	}
}

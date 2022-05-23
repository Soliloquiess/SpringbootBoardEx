package com.util.io;

import java.util.List;

import com.board.vo.BoardVO;

public class BoardPrint {

	// 1. 게시판 리스트 출력
	// list(데이터를 받는다.)
	public void list(List<BoardVO> list) {
		// 타이틀
		Out.titleWithLine("일반 게시판 리스트");
		int cnt = 65;
		// 제목 출력
		Out.line("-", cnt);
		System.out.println("   번호   |        제목       |    작성자    |    작성일    |   조회수 ");
		Out.line("-", cnt);
		// list가 null이거나 list의 size()가 0이면 데이터가 없는 것이다.
		if (list == null || list.size() == 0) System.out.println("    데이터가 존재 하지 않습니다.");
		// 데이터가 있는 경우의 처리
		else {
			// 데이터 여러개 -> 반복문 - foreach
			for(BoardVO vo : list) {
				System.out.println(" " + vo.getNo()
				+ "  |  " + vo.getTitle() + "  |  " + vo.getWriter() + "  |  "
				+vo.getWriteDate() + "  |  " + vo.getHit());
			}
		}
		Out.line("-", cnt);
	}
	
	// 2. 게시판 글보기
	public void view(BoardVO vo) {
		// 타이틀
		Out.titleWithLine("일반 게시판 글보기");
		// 데이터 출력
		Out.menuWithLine("- 글번호 : " + vo.getNo(),
				"- 제 목 : " + vo.getTitle(),
				"- 내 용 : " + vo.getContent(),
				"- 작성자 : " + vo.getWriter(),
				"- 작성일 : " + vo.getWriteDate(),
				"- 조회수 : " + vo.getHit());
	}
	
}

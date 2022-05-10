package com.print;

import java.util.List;

import com.board.vo.BoardVO;

public class BoardPrint {
	//1.List 출력
	public void list(List<BoardVO> list) {
		//제목
		System.out.println("+===============+");
		System.out.println("+게시판 리스트+");
		System.out.println("+===============+");

		//항목
		System.out.println("+===============+");
		System.out.println(" 번호| 제목 | 작성자 | 작성일 | 조회수 ");
		System.out.println("+===============+");
		
		//데이터
		for (BoardVO vo :list) {
			System.out.print(" "+ vo.getNo());
			System.out.print("|"+ vo.getTitle());
			System.out.print("|"+ vo.getWriter());
			System.out.print("|"+ vo.getWriteDate());
			System.out.print("|"+ vo.getHit());

			System.out.println();
		}
		System.out.println();
	}//end of list;
	
	public void view(BoardVO vo) {
		//제목
		System.out.println("+===============+");
		System.out.println("+게시판 글쓰기+");
		System.out.println("+===============+");
		
		//데이터 출력 - 항목 이름 : 데이터

		System.out.println("+===============+");
		
		System.out.print("번호 : "+ vo.getNo());
		System.out.print("제목 : "+ vo.getTitle());
		System.out.print("내용 : "+ vo.getContent());
		System.out.print("작성자 : "+ vo.getWriter());
		System.out.print("작성일 : "+ vo.getWriteDate());
		System.out.print("조회수 : "+ vo.getHit());
		System.out.println("+===============+");
	}
}

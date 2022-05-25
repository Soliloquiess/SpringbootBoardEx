package com.board.controller;

import com.main.controller.Controller;
import com.util.Input;
import com.util.Output;

public class BoardController implements Controller {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		// 게시판 처리 위한 무한 루프

		while (true) {
			// 위치 정보 출력
			Output.position("main>board");
			// 메뉴 출력
			Output.menu("1.게시판 리스트 2. 게시판 글 보기 3.게시판 글 쓰기 4. 게시판 글 수정 5. 게시판 글 삭제 0. 종료");
			// 메뉴 입력
			String menu = Input.getString("메뉴입력");
			// 메뉴 처리
			switch (menu) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case"6":
				break;
			}
		}
	}

}

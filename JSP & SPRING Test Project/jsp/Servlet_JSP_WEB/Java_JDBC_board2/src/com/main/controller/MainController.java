package com.main.controller;

import com.board.controller.BoardController;
import com.util.Input;
import com.util.Output;

//maincontroller->controller->service->dao의 구조로 실행
public class MainController {

	static {
		System.out.println("MainController 실행 시작");
		// 프로젝트가 시작과 동시에 처리해야할 일을 여기 넣는다.
		// DB드라이버 확인
		try {
			// 1.드라이버 확인한다 - 모든 DB처리 프로그램에서 처리 안해도 된다.
			Class.forName("com.util.DBInfo");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 프로그램 시작하면서 환영인사
		Output.greeting("ㅎㅇ");
		// 메인처리 무한루프
		while (true) {
			// 위치출력
			Output.position("main");
			// 메뉴출력
			Output.menu("1.공지사항 2. 이미지 3.질답 4. 게시판 5. 메시지 6.로그인 0 종료");
			// 메뉴입력
			String menu = Input.getString("메뉴입력");
			// 메뉴처리
			switch (menu) {
			case "1":
				System.out.println("1.공지사항 처리");
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				System.out.println("4.게시판 처리");
				execute(new BoardController());
				break;
			case "5":
				break;
			case "0":
				// 헤어짐 인사
				Output.greeting("사용해줘서 ㄳ");
				System.exit(0);
				break;

			}
		} // end of while(true)
	}// end of main()
		
	// moduleController를 실행하는 메서드 - 인터페이스
	private static void execute(Controller controller) {
		Output.exePos(controller.getClass().getSimpleName()+".execute()");
		controller.execute();
	}

}

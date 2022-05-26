/*
 * 메인 메뉴를  입력하고 어떤 객체를 처리할지를 선택해서 처리해 주는 객체(Controller)
 */
package com.webjjang.main.controller;

import com.webjjang.board.controller.BoardController;
import com.webjjang.image.controller.ImageController;
import com.webjjang.notice.controller.NoticeController;
import com.webjjang.util.Input;
import com.webjjang.util.Output;

public class MainController {

	//  static 블록 - static 초기화 : DB의 드라이버 확인 - DB 드라이버 프로그램 안에 static이 로딩된다.
	static {
		System.out.println("MainController 실행 시작");
		// 프로젝트가 시작과 동시에 처리해야할 내용을 여기에 넣는다.
		// DB 드라이버 확인
		try {
			// 1.드라이버 확인을 한다. - 모든 DB처리 프로그램에서 처리를 하지 않아도 된다.
			Class.forName("com.webjjang.util.DBInfo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 프로그램 시작하면서 환영인사
		Output.greeting("방가~ 방가~ 환영합니다.");
		
		// 메인처리 무한 루프
		while(true) {
			// 위치 출력
			Output.position("main");
			// 메뉴 출력
			Output.menu("1.공지사항  2.이미지  3.질문답변", "4.게시판  5.메시지  6.로그인", "0.종료");
			// 메뉴 입력
			String menu = Input.getString("메뉴 입력");
			// 메뉴 처리
			switch(menu) {
			case "1":
				System.out.println("1.공지 사항 처리");
				// 생성 -> 호출
				execute(new NoticeController());
				break;
			case "2":
				System.out.println("2. 이미지 처리");
				// 생성 -> 호출
				execute(new ImageController());
				break;
			case "3":
				
				break;
			case "4":
				System.out.println("4. 게시판 처리");
				// 생성 -> 호출
				execute(new BoardController());
				break;
			case "5":
				
				break;
			case "6":
				
				break;
			case "0":
				// 헤이짐 인사
				Output.greeting("사용해 주셔서 감사합니다. 좋은 하루~~");
				// 종료
				System.exit(0);
			default:
				Output.printWithLine("잘못된 메뉴를 선택하셨습니다.", "%", 40);
				break;
			}
		} // end of while(true)
		
	} // end of main()

	// moduleController를 실행하는 메서드 - 인터페이스 : 프록시 프로그램 패턴 - 필터(JSP), 인터셉터(Spring)
	private static void execute(Controller controller) {
		// 공통 처리
		// 정보 출력(로그 출력)
		Output.exePos(controller.getClass().getSimpleName() + ".execute()");
		// 컨트롤러 실행 -> 호출한다.
		controller.execute();
	}
	
}

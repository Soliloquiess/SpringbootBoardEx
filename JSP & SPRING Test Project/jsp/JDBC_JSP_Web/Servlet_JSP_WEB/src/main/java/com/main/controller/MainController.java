package com.main.controller;

/**
 *  <h2>웹커뮤니티 사이트 개발</h2>
 *  회원들의 의사교환의 장으로 작성. 정보교환을 목적으로 한다.<p>
 *  - 공지사한, 이미지 게시판, 질문답변, 일반게시판, 회원관리, 메시지<p>
 *  - 로그인/로그아웃이나 회원관리는 회원관리 메뉴에서 처리한다.<p>
 *  작성자 : 이영환 
 */

import com.board.controller.BoardController;
import com.image.controller.ImageController;
import com.member.controller.MemberContoller;
import com.message.controller.MessageController;
import com.util.io.In;
import com.util.io.Out;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Out.titleWithLine("Webjjang Community에 오신 것을 환영합니다.", 40);
		Out.title("시스템 준비 중...");
		
		try {
			Class.forName("com.util.db.DBInfo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shutdown("오류 : " + e.getMessage(), 1);
		}
		
		// 끝내라고 할때 까지 무한 루프 시작
		while(true) {
			
			Out.title("메인 메뉴");
			// 메뉴를 출력한다.
			Out.menuWithLine("1.공지사항, 2.이미지, 3.질문답변,",
					"4.일반게시판, 5.회원관리, 6.메시지, 0.종료",
					"** 회원가입, 로그인, 로그아웃은 회원관리 메뉴를 이용하세요.");
			// 메뉴 선택
			String menu = In.getString("메뉴 선택");
			// 메뉴 처리
			switch(menu) {
			case "1":
				System.out.println("공지사항 처리");
				// 게시판을 처리하는 클래스로 객체를 생성한다. - new -> 필요에 의해서 하드디스크에서 메인 메모리로 올린다.생성한다.
				// 생성할때 호출하는 넘을 생성자 - NoticeController() : 없으면 컴파일러가 자동으로 만든다.
				break;
			case "2":
				System.out.println("이미지 처리");
				// 생성한다.
				(new ImageController()).execute();
				break;
			case "3":
				System.out.println("질문답변 처리");
				// 생성
				break;
			case "4":
				System.out.println("게시판 처리");
				// 게시판을 처리하는 클래스로 객체를 생성한다. - new -> 필요에 의해서 하드디스크에서 메인 메모리로 올린다.생성한다.
				// 생성할때 호출하는 넘을 생성자 - BoardController() : 없으면 컴파일러가 자동으로 만든다.
				(new BoardController()).execute(); // 호출 -> execute()이동해서 처리하고 돌아온다.
				break;
			case "5":
				System.out.println("회원관리 처리");
				// 생성
				// 호출 - 실행하고 돌아온다.
				new MemberContoller().execute();
				break;
			case "6":
				System.out.println("메시지 처리");
				// 생성
				// 호출 - 실행하고 돌아온다.
				new MessageController().execute();
				break;
			case "0":
				shutdown("사용해 주셔서 감사합니다. 좋은 하루되세요.", 0);
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
			
		} // 무한 루프 끝

	} // main()의 끝

	public static void shutdown(String msg, int x) {
		// TODO Auto-generated method stub
		Out.title("시스템 종료");
		Out.menuWithLine(msg,"프로그램을 종료합니다.");
		System.exit(x);
	} // shutdown()의 끝
	

	
}

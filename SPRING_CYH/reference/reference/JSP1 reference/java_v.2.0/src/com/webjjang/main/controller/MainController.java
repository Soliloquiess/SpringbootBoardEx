package com.webjjang.main.controller;

import java.util.Scanner;

import com.webjjang.board.controller.BoardController;
import com.webjjang.image.controller.ImageController;
import com.webjjang.member.controller.LoginContoller;
import com.webjjang.util.io.In;

public class MainController {

	// 자동으로 올라간다.
	// 사용하려면 같은 클래스에서는 scanner.nextLine()
	// 다른 클래스에서 사용 MainControler.scanner.nextLine()
	// 속성 = 필드 = 아이템 = 객체변수 = 멤버변수 = 전역변수
//	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 끝내라고 할때 까지 무한 루프 시작
		while(true) {
			
			System.out.println("\n\n\n=========[메인 메뉴]===============\n");
			// 메뉴를 출력한다.
			System.out.println("1.게시판, 2.이미지, 3.로그인, 0.종료");
			// 메뉴 선택
			String menu = In.getString("메뉴 선택");
			// 메뉴 처리
			switch(menu) {
			case "1":
				System.out.println("게시판 처리");
				// 게시판을 처리하는 클래스로 객체를 생성한다. - new -> 필요에 의해서 하드디스크에서 메인 메모리로 올린다.생성한다.
				// 생성할때 호출하는 넘을 생성자 - BoardController() : 없으면 컴파일러가 자동으로 만든다.
				BoardController boardController = new BoardController();
				System.out.println(boardController);
				boardController.execute(); // 호출 -> execute()이동해서 처리하고 돌아온다.
				break;
			case "2":
				System.out.println("이미지 처리");
				// 생성한다.
				ImageController imageController = new ImageController();
				System.out.println("MainController.imageContoller : " + imageController);
				// ImageController 안에 실행할 메서드를 호출한다.
				imageController.execute();
				break;
			case "3":
				System.out.println("로그인 처리");
				// 생성
				LoginContoller loginContoller = new LoginContoller();
				// 호출 - 실행하고 돌아온다.
				loginContoller.execute();
				break;
			case "0":
				System.out.println("프로그램 종료");
				System.exit(0);;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
			
		} // 무한 루프 끝

	}

}

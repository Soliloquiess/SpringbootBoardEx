package com.board.controller;

import java.util.List;
import java.util.Scanner;

import com.board.service.BoardDeleteService;
import com.board.service.BoardListService;
import com.board.service.BoardUpdateService;
import com.board.service.BoardViewService;
import com.board.service.BoardWriteService;
import com.board.vo.BoardVO;
import com.print.BoardPrint;

/*
 * Controller = 요구한 내용을 가지고 어떤 객체를 실행할 지 정해주는 클래스
 *            = 필요한 데이터 수집하고 실행되는 객체에 넘겨준다.
 */
public class BoardController {

	public static Scanner scanner = new Scanner(System.in);

	// 실행의 처음 main을 만든다. 다른곳에서는 main이 동작되면 안된다.
	public static void main(String[] args) throws Exception {
		// 무한반복
		while (true) {

			// DB클래스 확인
			Class.forName("com.util.db.DB");

			// 메뉴 출력
			System.out.println("=======================");
			System.out.println("1. 게시판 리스트 2. 게시판 글보기 3.게시판 글 쓰기 ");
			System.out.println("4. 게시판 글수정 5. 게시판 글삭제 6.프로그램 종료 ");

			System.out.println("=======================");
			// 메뉴 선택
			System.out.println("메뉴 선택");
			String menu = scanner.nextLine();
			// 메뉴 처리

			switch (menu) {
			case "1":
				// 게시판 리스트 처리 -> 생성하고 호출한다. : Controller - Service - DAO
				BoardListService boardListService = new BoardListService();
				List<BoardVO> list = boardListService.service();
				// 데이터 확인용으로 출력
				System.out.println("boardController.main.list" + list);
				// 가져온 데이터 출력하기 - 제목, 항목 이름, 데이터 - 생성하고 호출한다.
				BoardPrint boardPrint = new BoardPrint();
				boardPrint.list(list);
				break;

			case "2":
				// 게시판 글보기 처리 -> 생성하고 호출한다. : Controller - Service - DAO
				// 글 보기를 위해서 필요한 정보 - 글 번호를 입력한다.
				System.out.println(">번호:");
				String strNo = scanner.nextLine();
				long no = Long.parseLong(strNo);

				// 게시판 글보기 처리
				BoardViewService boardViewService = new BoardViewService();
				BoardVO vo = boardViewService.service(no);

				// 데이터 확인용으로 출력
				System.out.println("BoardController.main().vo" + vo);

				// 가져온 데이터 출력하기 = =제목, 항목이름 : 데이터 - 생성하고 호출한다.
				boardPrint = new BoardPrint();
				if (vo == null) {
					System.out.println("없는 글번호입니다.");
					break;
				}
				boardPrint.view(vo);
				break;

			case "3":
				// 데이터 수집 - 제목 , 내용 , 작성자
				System.out.println("제목:");
				String title = scanner.nextLine();
				System.out.println("내용:");
				String content = scanner.nextLine();
				System.out.println("작성자");
				String writer = scanner.nextLine();
				// BoardVO 생성하고 데이터를 저장한다.
				vo = new BoardVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				// Controller - Service -DAO
				BoardWriteService boardWriteService = new BoardWriteService();
				boardWriteService.service(vo);
				break;

			case "4":
				// 수정할 글 번호를 받는다.
				System.out.println("수정할 글 번호 입력: ");
				strNo = scanner.nextLine();
				no = Long.parseLong(strNo);
				// 입력할 글 번호에 데이터를 가져와서 출력 -글 보기
				boardViewService = new BoardViewService();
				vo = boardViewService.service(no);

				// 가져온 데이터 출력하기 - 제목, 항목이름: 데이터 생성하고 호출한다.
				boardPrint = new BoardPrint();
//			boardPrint.view(vo);(아래로 이동)

				String item = "1";
				// 수정할 내용을 입력한다. 1- 제목 2- 내용 3- 작성자 0- 수정 9- 취소
				// while(item.equals("1")||item.equals("2")||item.equals("3")) {
				while (!item.equals("0") && !item.equals("9")) {

					// 데이터 보여주기(수정할 떄마다) - DB에 적용이 되어있지는 않는다.
					boardPrint.view(vo);
					System.out.println("1.제목 2. 내용 3. 작성자 0 수정완료 9 취소");
					System.out.print("수정할 항목을 선택->");
					item = scanner.nextLine();
					switch (item) {
					case "1":
						System.out.println("제목: ");
						vo.setTitle(scanner.nextLine());
						break;
					case "2":
						System.out.println("내용: ");
						vo.setContent(scanner.nextLine());
						break;
					case "3":
						System.out.println("작성자: ");
						vo.setWriter(scanner.nextLine());
						break;
					// 수정 처리를 한다
					// 수정 처리를 한다.

					case "0": // 수정 완료 처리
						// 객체를 생성하고 호출한다.
						BoardUpdateService boardUpdateService = new BoardUpdateService();
						boardUpdateService.service(vo);
						System.out.println("---------------");
						System.out.println("수정 완료");
						System.out.println("---------------");

					case "9": // 수정 완료 처리
						// 객체를 생성하고 호출한다.
						System.out.println("---------------");
						System.out.println("수정 취소");
						System.out.println("---------------");

					default:
						System.out.println("---------------");
						System.out.println("잘못된 항목 선택");
						System.out.println("---------------");

						break;
					}
				}
				// 수정 처리를 한다.
				break;
			case "5":
				// 삭제처리
				// 삭제할 글 번호 받기
				
				System.out.println("삭제할 글 번호 입력: ");
				strNo = scanner.nextLine();
				no = Long.parseLong(strNo);

				// 생성하고 호출한다.
				BoardDeleteService boardDeleteService = new BoardDeleteService();
				boardDeleteService.service(no);
				break;
			case "0":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}

		}
	}
}

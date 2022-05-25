package com.webjjang.board.controller;

import java.util.List;

import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.exception.CommonException;
import com.webjjang.util.io.BoardPrint;
import com.webjjang.util.io.In;

public class BoardController {

	// BopardController() 생성자가 없다. -> compiler 가 자동으로 만들어서 class로 만들어 준다. - 기본 생성자 - 생략 가능
	// 생성자 만들기 - 클래스 이름과 같다. 리턴타입이 없다.-> 주소만 리턴하기 때문에
	public BoardController() {
		System.out.println("BoardController() 생성자");
	}
	
	public void execute(){
		System.out.println("BoardController.execute()");
		
		System.out.println("\n\n-----------[일반 게시판]---------------\n");
		//무한 루프 시작
		while(true)
		{
			// 무한 루프 빠져나가기 -> 메서드 빠져나가기 0일때 return
			System.out.println("\n----------[게시판 메뉴]--------------------");
			
			// 게시판 메뉴 출력
			System.out.println("1. 리스트  2. 글보기  3. 글쓰기");
			System.out.println("4. 글수정  5. 글삭제  0. 이전메뉴");
		
			String menu = In.getString("메뉴 입력");
			
			// 리스트와 글보기 출력을 위한 객체 선언
			BoardPrint boardPrint = null;
			
			// 예외가 발생이 되면 catch로 가서 실행하세요. 예외처리
			try { // 정상처리
				// 입력된 메뉴 처리
				switch(menu) {
				case "1":
					System.out.println("BoardContllore.execute() - 게시판 리스트 처리함.");
					// 리스트 서비스 클래스를 생성하고 메서드를 호출한다.
					// 생성 - 필요한 프로그램을 메인메모리에 올린다.
					BoardListService boardListService = new BoardListService();
					List<BoardVO> list = boardListService.service();
					System.out.println("BoardController.execute().list - " + list);
					// 데이터를 출력
					// 출력하는 객체를 생성 -> 호출
					boardPrint = new BoardPrint();
					boardPrint.list(list);
					break; // switch, 반복(for, while) 빠져나간다.
					// continue -> 반복문(for, while)의 처음으로 이동한다.
				case "2":
					// long 타입의 데이터로 변환
					long no = In.getLong("보고 싶은 글번호");
					
					// BoardController -> BoardViewService.service(long no) -> BoardDAO.view(long no) : 글번호 전달
					// DB에 있는 데이터(BoardVO)를 BoardController까지 가져온다. BoardPrint.view(BoardVO vo)에서 출력한다.
					// BoardViewService를 객체 생성 메서드를 호출한다.
					BoardViewService boardViewService = new BoardViewService();
					BoardVO vo = boardViewService.service(no);
					System.out.println("BoardController.execute().vo - " + vo);
					// BoardPrint를 이용해서 게시글 출력
					boardPrint = new BoardPrint();
					boardPrint.view(vo);
					
					break;
				case "3":
					System.out.println("게시판 글쓰기 처리함.");
					// 데이터(BoardVO - 제목, 내용, 작성) -> 복잡 -> 메서드 inWrite()
					BoardVO inVO = inWrite();
					System.out.println("BoardController.execute() 글쓰기를 위한 데이터 vo : " + inVO);
					// BoardWriteService 보낸다. service(BoardVO vo)
					// 객체 생성하고 메서드 호출 - 메서드 호출할 때 vo를 보내야한다.
					// 객체 생성
					BoardWriteService boardWriteService = new BoardWriteService();
					// 메서드 호출
					boardWriteService.service(inVO);
					break;
				case "4":
					System.out.println("게시판 글수정 처리함.");
					// 1. 수정할 글번호 입력(리스트에서 확인하고 있는 글번호를 입력받아야 한다.
					long upNo = In.getLong("수정할 글번호");
					// 2. 글번호에 맞는 데이터를 가져온다. -> 글보기 -> BoardViewService
					//   생성
					BoardViewService boardViewService2 = new BoardViewService();
					//   호출
					BoardVO updateVO = boardViewService2.service(upNo);
					// 가져온 데이터 확인
					System.out.println("BoardController.execute().updateVO - " + updateVO);
					// 자바에서 updateVO객체의 데이터를 수정한다. - 제목, 내용, 작성자 -> 메서드
					String result = updateVO(updateVO); // "0" 수정하러 간다. "9" - 취소되었다고 출력한다.
					if(result.equals("0")) {
						// 수정 처리를 한다.
						// BoardUpdateService.service(BoardVO vo) -> BoardDAO.update(BoardVO vo)
						BoardUpdateService boardUpdateService = new BoardUpdateService();
						// updateResult ==0 : 수정이 안됨.글번호 맞지 않는다. updateResult > 0 수정이 되었다.
						int updateResult = boardUpdateService.service(updateVO);
					} else System.out.println("수정이 취소되었습니다."); //"9" - 취소
					break;
				case "5":
					System.out.println("게시판 글삭제 처리함.");
					// 삭제할 글번호를 받는다. String -> long 바꾼다.(리스트에서 있는 글번호를 확인해서 입력한다.)
					long delNo = In.getLong("삭제할 글번호");
					// 삭제 처리할 객체를 생성하고 호출한다. BoardDeleteService -> 새로 만든다.
					BoardDeleteService boardDeleteService = new BoardDeleteService();
					int delResult = boardDeleteService.service(delNo);
					System.out.println("BoardController.execute().delResult - " + delResult);
					break;
				case "0":
					return; // method를 빠져 나간다.
				default :
					System.out.println("잘못된 메뉴를 입력하셨습니다.");
					break;
				
				}//switch{} 의 끝
			} catch (Exception e) {
//				System.out.println("============[ 모듈 오류 ]==============");
//				System.out.println(" + 게시판 처리 중 오류 남.");
//				System.out.println(" + message : " + e.getMessage());
				CommonException.print("게시판 처리 중 오류 발생", e);
			}
		
		} // 무한 루프(while) 끝
	} // execute()의 끝
	
	
	// 제목, 내용, 작성자 데이터를 입력 받아서 데이터가 채워진 vo 객체가 나온다.============
	BoardVO inWrite(){
		// vo 객체 생성
		BoardVO vo = new BoardVO();
		
		// 데이터 채우기 - 제목, 내용, 작성자
		vo.setTitle(In.getString("제목"));
		vo.setContent(In.getString("내용"));
		vo.setWriter(In.getString("작성자"));
		
		return vo;
	}
	
	// 수정 처리문 -> 메서드
	String updateVO(BoardVO vo) {
		System.out.println("BoardController.updateVO().vo - " + vo);
		
		// 데이터 수정 - 입력이 0이 들어올때까지 무한 반복
		while(true) {
			System.out.println("1. 제목   2. 내용   3. 작성자   9. 수정 취소   0. 수정 적용");
			String menu = In.getString("항목 선택");
			switch (menu) {
			case "1":
				vo.setTitle(In.getString("제목"));
				break;
			case "2" :
				vo.setContent(In.getString("내용"));
				break;
			case "3" :
				vo.setWriter(In.getString("작성자"));
				break;
			case "9" :
				return "9";
			case "0" :
				return "0";
			default:
				System.out.println("잘못된 항목을 선택하셨습니다.");
				break;
			} // switch 문의 끝
			System.out.println("BoardController.updateVO().vo - " + vo);
		}
		
	}
	
} // 클래스의 끝

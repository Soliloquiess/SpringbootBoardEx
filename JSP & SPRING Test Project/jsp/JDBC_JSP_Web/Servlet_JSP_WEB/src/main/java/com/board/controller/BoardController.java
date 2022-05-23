package com.board.controller;

import java.util.List;

import com.board.service.BoardDeleteService;
import com.board.service.BoardListService;
import com.board.service.BoardUpdateService;
import com.board.service.BoardViewService;
import com.board.service.BoardWriteService;
import com.board.vo.BoardVO;
import com.main.controller.ExecuteService;
import com.util.exception.CommonException;
import com.util.io.BoardPrint;
import com.util.io.In;
import com.util.io.Out;

public class BoardController {

	// BopardController() 생성자가 없다. -> compiler 가 자동으로 만들어서 class로 만들어 준다. - 기본 생성자 - 생략 가능
	// 생성자 만들기 - 클래스 이름과 같다. 리턴타입이 없다.-> 주소만 리턴하기 때문에
	public BoardController() {
		System.out.println("BoardController() 생성자");
	}
	
	// Object -> List<BoardVO> 캐시팅할 때 경고가 나는데 그걸 숨기도록 지시했다.(어노테이션)
	@SuppressWarnings("unchecked")
	public void execute(){
		System.out.println("BoardController.execute()");
		
		//무한 루프 시작
		while(true)
		{
			// 무한 루프 빠져나가기 -> 메서드 빠져나가기 0일때 return
			Out.title("게시판 메뉴");
			
			// 게시판 메뉴 출력 Out.menuWithLine(1줄메뉴,2줄메뉴)
			Out.menuWithLine("1. 리스트  2. 글보기  3. 글쓰기", "4. 글수정  5. 글삭제  0. 이전메뉴");
		
			String menu = In.getString("메뉴 입력");
			
			// 예외가 발생이 되면 catch로 가서 실행하세요. 예외처리
			try { // 정상처리
				// 입력된 메뉴 처리
				switch(menu) {
				case "1":
					System.out.println("BoardContllore.execute() - 게시판 리스트 처리함.");
					// 리스트 서비스 클래스를 생성하고 메서드를 호출한다.
					// 생성 - 필요한 프로그램을 메인메모리에 올린다.
//					new BoardPrint().list(new BoardListService().service(null));
					new BoardPrint().list((List<BoardVO>)ExecuteService.execute(new BoardListService(), null));
					break; // switch, 반복(for, while) 빠져나간다.
					// continue -> 반복문(for, while)의 처음으로 이동한다.
				case "2":
					// 1. BoardPrint() 생성
					// 2. view() 안에 있는 BoardViewService() 생성
					// 3. service() 안에 잇는 In.getLong() Long 타입의 데이터를 받는다.
					// 4. setvice()를 이용해서 DB 에서 BoardVO 객체를 가져온다.
					// 5. view()를 이용해서 가져온 데이터를 출력한다.
					new BoardPrint().view((BoardVO)ExecuteService.execute(new BoardViewService(), In.getLong("보고 싶은 글번호")));
					
					break;
				case "3":
					System.out.println("게시판 글쓰기 처리함.");
					// 1. BoardWriteService() 생성
					// 2. 데이터(BoardVO - 제목, 내용, 작성) -> 복잡 -> 메서드 inWrite()
					// 3. service() 호출해서 DB에 등록처리한다.
					ExecuteService.execute(new BoardWriteService(),inWrite());
					break;
				case "4":
					System.out.println("게시판 글수정 처리함.");
					// 1. 수정할 데이터 가져오기
					BoardVO vo = (BoardVO) ExecuteService.execute(new BoardViewService(),In.getLong("수정할 글번호"));
					if(updateVO(vo).equals("0")) {// "0" 수정하러 간다. "9" - 취소되었다고 출력한다.
						// 수정 처리를 한다.
						int updateResult = (int) ExecuteService.execute(new BoardUpdateService(),vo);
					} else System.out.println("수정이 취소되었습니다."); //"9" - 취소
					break;
				case "5":
					System.out.println("게시판 글삭제 처리함.");
					int delResult = (int) ExecuteService.execute(new BoardDeleteService(),In.getInt("삭제할 글번호"));
					break;
				case "0":
					return; // method를 빠져 나간다.
				default :
					System.out.println("잘못된 메뉴를 입력하셨습니다.");
					break;
				
				}//switch{} 의 끝
			} catch (Exception e) {
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

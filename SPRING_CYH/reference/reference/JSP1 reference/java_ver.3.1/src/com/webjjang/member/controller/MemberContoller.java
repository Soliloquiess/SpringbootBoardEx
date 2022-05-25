package com.webjjang.member.controller;

import com.webjjang.main.controller.ExecuteService;
import com.webjjang.member.service.LoginService;

/**
 * 회원관리를 위한 MemberController
 * 1. 로그인 / 로그아웃, 2. 회원가입 / 내 정보 보기 
 * [로그인이 되어 있을 때] 3. 내정보 수정, 4.비밀번호 변경
 * [로그인이 안되어 있을 때] 3. 아이디찾기, 4.비밀번호 찾기
 */

import com.webjjang.member.vo.Login;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.exception.CommonException;
import com.webjjang.util.io.In;
import com.webjjang.util.io.Out;

public class MemberContoller {

	public void execute() {
		System.out.println("MemberController.execute");
		
		// 무한 루프 시작
		while(true) {
			Out.title("회원관리 메뉴");
			
			// Controller에서 예외처리
			try {
				// 로그인이 되어 있으면 logined()호출, 로그인이 안되어 있으면 login()호출
//				boolean isLoop = false;
//				if(Login.isLogin()) isLoop = logined();
//				else isLoop = login();
//				if(!isLoop) return;
				// 호출한 메서드가 false이면 리턴한다.
//				boolean isLoop = (!Login.isLogin())?login():logined();
//				System.out.println("MemberController.execute().isLoop : " + isLoop);
//				if(!isLooop) return;
				if(!((!Login.isLogin())?login():logined())) return;
			} catch (Exception e) {
				// TODO: handle exception
				CommonException.print("회원관리 처리 중 오류 발생", e);
			}
		} // 무한 루프 (while) 끝
	}// execute() 의 끝
	
	// 로그인이 되어 있을 때 처리하는 메서드
	boolean logined() throws Exception{
		// 메뉴 출력 - 
		Out.menuWithLine("1.로그아웃  2.내 정보보기 3.내 정보수정 4.비밀번호 변경",
				"5.회원탈퇴 6.회원리스트(관리자) 0.이전메뉴");
		// 메뉴 입력
		String menu = In.getString("메뉴 입력");
		// 메뉴 처리 - try가 없다.
		switch(menu) {
		case "1":
			System.out.println("로그아웃처리");
			// login 정보 지우기
			Login.logout(); // null 데이터가 없다.
			Out.titleWithLine("로그아웃되었습니다.", 20);
			break;
		case "2":
			System.out.println("내 정보보기처리");
			break;
		case "3":
			System.out.println("내 정보수정처리");
			break;
		case "4":
			System.out.println("비밀번호 변경처리");
			break;
		case "5":
			System.out.println("회원탈퇴처리");
			break;
		case "0": // 이전 메뉴 - 반복 처리 안함.
			return false;
		default:
			Out.titleWithLine("잘못된 메뉴를 선택하셨습니다.");
			break;
		}
		return true; // 계속 반복 처리한다.
	}
	
	// 로그인이 되어 있는 않을 때 처리하는 메서드
	boolean login() throws Exception {
		// 메뉴 출력 - 
		Out.menuWithLine("1.로그인  2.회원가입 3.아이디찾기 4.비밀번호 찾기 0.이전메뉴");
		// 메뉴 입력
		String menu = In.getString("메뉴 입력");
		// 메뉴 처리 - try가 없다.
		switch(menu) {
		case "1":
			System.out.println("로그인처리");
			// 아이디와 비밀번호를 받는다.
			LoginVO vo = new LoginVO();
			vo.setId(In.getString("아이디"));
			vo.setPw(In.getString("비밀번호"));
			// DB에서 데이터를 가져온다. -> LoginService -> LoginDAO
			// 생성& 호출
//			new LoginService().service(vo); // 로그가 찍히지 않는다. -> ExecuteService 사용한다.
//			LoginVO outVO = (LoginVO) ExecuteService.execute(new LoginService(), vo);
			// 가져온 데이터가 null이면 입력 정보 오류 -> 예외발생 자동처리
			// null이 아니면 login변수에 정보를 저장한다.
//			Login.setLogin(outVO);
			if(Login.setLogin((LoginVO) ExecuteService.execute(new LoginService(), vo)))
				Out.title("로그인 처리가 되었습니다.");
			else
				throw new Exception("로그아웃 처리 후 로그인을 해 주세요.");
			break;
		case "2":
			System.out.println("회원가입 처리");
			break;
		case "3":
			System.out.println("아이디 찾기 처리");
			break;
		case "4":
			System.out.println("비밀번호 찾기 처리");
			break;
		case "0": // 이전 메뉴 - 반복 처리 안함.
			return false;
		default:
			Out.titleWithLine("잘못된 메뉴를 선택하셨습니다.");
			break;
		}
		return true; // 계속 반복 처리한다.
	}
	
}

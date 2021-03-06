package com.webjjang.message.controller;

import java.util.List;

import com.webjjang.main.controller.ExecuteService;
import com.webjjang.member.vo.Login;
import com.webjjang.message.service.MessageListService;
import com.webjjang.message.service.MessageWriteService;
import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.exception.CommonException;
import com.webjjang.util.io.In;
import com.webjjang.util.io.MessagePrint;
import com.webjjang.util.io.Out;

public class MessageController {

	@SuppressWarnings("unchecked")
	public void execute() {
		System.out.println("MessageController.execute()");
		
		// 로그인 체크
		// 로그인이 안되어 있는 경우 처리
		if(!Login.isLogin()) {
			Out.titleWithLine("로그인이 필요한 페이지입니다.");
			// 메인 메뉴로 이동
			return;
		}
		
		while(true) {
			Out.title("메시지 메뉴");
			Out.menuWithLine("1.리스트  2.보기  3.쓰기  4.삭제  0.이전메뉴");
			String menu = In.getString("메뉴 입력");
			try {
				switch (menu) {
				case "1":
					System.out.println("메시지 리스트 처리");
					// 생성하고 호출 - ExecuteService 클래스 사용
					new MessagePrint().list((List<MessageVO>) ExecuteService.execute(new MessageListService(), Login.getId()));
					break;
				case "2":
					System.out.println("메시지 보기 처리");
					break;
				case "3":
					System.out.println("메시지 쓰기 처리");
					// 데이터 수집 - 내용, 받는사람아이디 : 입력, 보내는 사람 아이디 :Login
					MessageVO vo = new MessageVO();
					vo.setContent(In.getString("메시지 내용"));
					vo.setAccepter(In.getString("받는사람아이디"));
					vo.setSender(Login.getId());
					// 생성하고 호출 - ExecuteService 클래스 사용
					ExecuteService.execute(new MessageWriteService(), vo);
					break;
				case "4":
					System.out.println("메시지 삭제 처리");
					break;
				case "0":
					return;
				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다.");
					break;

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				CommonException.print("메시지 처리 중 오류 발생", e);
			}
		}
	}
	
}

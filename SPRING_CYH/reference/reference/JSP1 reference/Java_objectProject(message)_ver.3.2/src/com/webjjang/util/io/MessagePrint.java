package com.webjjang.util.io;

import java.util.List;

import com.webjjang.message.vo.MessageVO;

public class MessagePrint {

	// 1. 메시지 리스트 출력
	// list(데이터를 받는다.)
	public void list(List<MessageVO> list) {
		// 타이틀
		Out.titleWithLine("메시지 리스트");
		int cnt = 75;
		// 제목 출력
		Out.line("-", cnt);
		System.out.println("   번호   |  보낸사람(보낸사람아이디)  | 보낸 날짜 | 받는사람(받는사람아이디) | 받은 날짜 ");
		Out.line("-", cnt);
		// list가 null이거나 list의 size()가 0이면 데이터가 없는 것이다.
		if (list == null || list.size() == 0) System.out.println("    데이터가 존재 하지 않습니다.");
		// 데이터가 있는 경우의 처리
		else {
			// 데이터 여러개 -> 반복문 - foreach
			for(MessageVO vo : list) {
				System.out.println(" " + vo.getNo()
				+ "  |  " + vo.getSenderName()+ "(" + vo.getSender() + ")" 
				+ "  |  " + vo.getSendDate()
				+ "  |  " + vo.getAccepterName()+ "(" + vo.getAccepter() + ")" 
				+ "  |  " + ((vo.getAcceptDate() == null)?"읽지 않음":vo.getAcceptDate()) );
			}
		}
		Out.line("-", cnt);
	}
	
	// 2. 메시지 글보기
	public void view(MessageVO vo) {
		// 타이틀
		Out.titleWithLine("일반 메시지 글보기");
		// 데이터 출력 - 메지시 번호, 내용, 보낸 사람 : 보낸사람이름(보낸사람아이디) , 보낸날짜, 받는사람의 정보
		Out.menuWithLine("번호 : " + vo.getNo(),
				"내용 : " + vo.getContent(),
				"보낸 사람 : " + vo.getSenderName() + "(" + vo.getSender() + ")",
				"보낸 날짜 : " + vo.getSendDate(),
				"받는 사람 : " + vo.getAccepterName() + "(" + vo.getAccepter() + ")",
				"읽은 날짜 : " + ((vo.getAcceptDate() != null)?vo.getAcceptDate():"읽지 않음"));
	}
	
}

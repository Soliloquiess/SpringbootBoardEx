package com.webjjang.member.vo;

import lombok.Data;

// lombok 라이브러리가 정상적으로 등록이 되서 실행되어야 한다.
@Data
public class LoginVO {

	private String id, pw, name, photo;
	private int gradeNo;
	private String gradeName;
	// 새로운 메시지를 저장하는 변수 -> 서브쿼리
	// 메세지 시스템에서 새 메시지를 읽으면 세션에 있는 새메시지 정보를 -1 처리를 해 줘야한다.
	private long newMessage;

}

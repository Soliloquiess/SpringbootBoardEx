package com.util.exception;

import com.util.io.Out;

public class CommonException {

	// 사용법 : CommonException.print(오류메시지, 예외객체)
	public static void print(String msg, Exception e) {
		Out.title("모듈 오류");
		Out.menuWithLine(msg, 
				"message : " + e.getMessage(),
				"해결 방법 : 다시 한번 실행보세요.",
				"          ");
	}
	
}

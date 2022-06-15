package com.webjjang.util.cookieUtil;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

@Log4j
public class CookieUtil {

	// 쿠키를 굽는 처리문
	public static void createCookie(String cookieName, String cookiePath, String cookieData,
  int cookieMaxAge,
  HttpServletResponse response) throws Exception {
  
		// 쿠키를 생성한다.(이름, 시간, 위치)
        Cookie storeIdCookie = new Cookie(cookieName, cookieData);
        // 쿠키의 위치 설정(클라이언트에 저장)
        storeIdCookie.setPath(cookiePath);
        // 쿠키의 유효시간 설정
        storeIdCookie.setMaxAge(cookieMaxAge);
        // 쿠키는 클라이언트 컴퓨터에 저장하기 때문에 response를 이용해서 서버에서 클라이언트로 쿠키를 전달한다.
        response.addCookie(storeIdCookie);
  
        log.info("** cookie를 생성했습니다.");
	}
	
	//메시지 쿠키 굽기 - 유효시간 5분
	public static void createMessageCookie(Integer msg, HttpServletResponse response) throws Exception {
		createCookie("msg", "/", "" + msg, 5 * 60, response);
		//최상위 (/)에 쿠키를 굽겠다
	}

}

package com.main.controller;

import javax.servlet.http.HttpServletRequest;

public interface Controller {
	//추상 메서드 정의
	//접근 제한 - public 
	//결과 데이터 리턴타입 - String.
	//메서드 명 - execute()
	//전달되는 데이터- request 전달, 데이터 수집. 처리된 데이터 저장
	//예외처리 - DispatcherServlet에게 전달한다. - throw 시킨다.
	
	
	public String execute(HttpServletRequest request) throws Exception;
	
}

package com.main.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface Controller {
	//추상 메서드 - 실행하고 어디로 가서 무엇을 처리하느냐 리턴타입.? 예외처리? 전달데이터?
	//접근 제한자 - public
	//생성 제한자 - non-static 
	//리턴타입 - jsp로 포워드(jsp로 이용해서 html만든다는 거) 또는 url이용해서 url페이지 정보 : String
	//다양한 데이터(List,VO, int) -object
	//메서드명 - execute()
	
	//전달되는 파라미터 - 데이터 수집. 처리된 결과 저장: 이게 request객체
	//예외처리 - throw시킨다.
	public String execute(HttpServletRequest request) throws Exception;
}

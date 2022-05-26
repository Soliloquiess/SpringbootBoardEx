/*
 * 처리를 해결하기 위한 객체 선언
 * 리턴 타입이나 매개변수가 특정한 것이 없이 여러 타입을 사용하게 되면 Object를 사용한다.
 * 예외처리를 Controller에서 처리하므로 여기서는 던진다.
 * 작성일 : 2021.01.18
 * 작성자 : 이영환
 */
package com.webjjang.main.controller;

public interface Service {

	public Object service(Object obj) throws Exception;
	
}

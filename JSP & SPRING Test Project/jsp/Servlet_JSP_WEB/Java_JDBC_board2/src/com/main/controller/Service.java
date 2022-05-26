/*
 * 처리를 해결하기 위한 객체 선언
 * 리턴 타입이나 매개변수가 특정한 것이 없이 여러 타입을 사용하게 되면 Object를 사용한다.
 * 예외처리를 Controller에서 처리하므로 여기서는 던진다.
 */
package com.main.controller;

public interface Service {

	public Object service(Object obj) throws Exception;	//Object 타입이니까 Object타입 리턴해야됨.
	
}

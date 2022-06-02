package com.util.bean;

import java.util.HashMap;
import java.util.Map;

import com.main.controller.Controller;

public class Beans {

	//생성하지 않고 바로 쓸 수 있는(static) 저장 변수
	private static Map<String, Controller> controllerMap= new HashMap<String,Controller>();
	//생성하지 않고 바로 쓸 수 있는 메서드
	
	//생성해서 전달한 객체를 저장해 놓는 메서드
	//key-게시판=>/board: /BoardController, 공지사항 = > /notice
	//url = /board/list.do - > 두번째 / 까지의 문자열 잘라내서 key로 사용한다.
	public static void put(String key, Controller controller) {
		controllerMap.put(key, controller);
	}
	//key에 따라서 Controller를 꺼내는 메서드
	public static Controller get(String key) {
		return controllerMap.get(key);
	}
}

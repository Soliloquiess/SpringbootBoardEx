package com.util.bean;

import java.util.HashMap;
import java.util.Map;

import com.main.controller.Controller;
import com.main.controller.Service;

public class Beans {

	// 생성하지 않고 바로 쓸 수 있는(static) 저장 변수
	private static Map<String, Controller> controllerMap = new HashMap<String, Controller>();
	private static Map<String, Service> serviceMap = new HashMap<>();
	private static Map<String, Object> daoMap = new HashMap<>();

	// 생성된 객체를 저장하는 메서드 - put()
	// controller를 저장하는 메서드
	// key- /board: 게시판
	public static void put(String key, Controller controller) {
		controllerMap.put(key, controller);
	}

	// service를 저장하는 메서드
	public static void put(String key, Service service) {
		serviceMap.put(key, service);
	}

	// Dao를 object로 저장하는 메서드
	public static void put(String key, Object dao) {
		daoMap.put(key, dao);
	}

	// 객체를 꺼내는 메서드 - get()
	// Controller꺼내기
	public static Controller getController(String key) {
		return controllerMap.get(key);

	}

	// Service 꺼내기
	public static Service getService(String key) {
		return serviceMap.get(key);

	}

	// dao꺼내기
	public static Object getDAO(String key) {
		return daoMap.get(key);

	}
}

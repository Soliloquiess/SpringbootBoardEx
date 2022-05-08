package com.webjjang.board.controller;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Service;
import com.webjjang.util.bean.Beans;

public class BoardController implements Controller {

	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		// 실행전 실행할 서비스 셋팅
		setService(url);
		return null;
	}

}

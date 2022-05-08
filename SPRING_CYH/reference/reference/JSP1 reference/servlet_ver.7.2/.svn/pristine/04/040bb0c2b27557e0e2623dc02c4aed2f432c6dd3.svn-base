package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;

public class MessageCountService implements Service {

	private MessageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// mode = 3으로 세팅되어 있다. 새로운 메시지의 전체 데이터 개수 구하는 메서드 호출
		return dao.getTotalRow((PageObject) obj);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

}

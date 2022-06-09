package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;

public class MessageWriteService implements Service {

	private MessageDAO dao;
	
	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// 전달받는 객체의 메서드를 호출한다.
		return dao.write((MessageVO) vo);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

}

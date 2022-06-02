package com.message.service;

import com.main.controller.Service;
import com.message.dao.MessageDAO;
import com.message.vo.MessageVO;

public class MessageWriteService implements Service {

	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// 생성하고 호출한다.
		return new MessageDAO().write((MessageVO) vo);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		
	}

}

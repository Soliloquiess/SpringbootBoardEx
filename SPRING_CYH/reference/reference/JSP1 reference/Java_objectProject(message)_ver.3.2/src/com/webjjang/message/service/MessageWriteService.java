package com.webjjang.message.service;

import com.webjjang.main.controller.ServiceInterface;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;

public class MessageWriteService implements ServiceInterface {

	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// 생성하고 호출한다.
		return new MessageDAO().write((MessageVO) vo);
	}

}

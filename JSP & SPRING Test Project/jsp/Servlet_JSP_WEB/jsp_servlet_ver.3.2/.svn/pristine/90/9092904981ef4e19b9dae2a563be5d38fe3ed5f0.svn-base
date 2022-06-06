package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;

public class MessageDeleteService implements Service {

	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// 생성하고 호출한다.
		return new MessageDAO().delete((MessageVO) vo);
	}

}

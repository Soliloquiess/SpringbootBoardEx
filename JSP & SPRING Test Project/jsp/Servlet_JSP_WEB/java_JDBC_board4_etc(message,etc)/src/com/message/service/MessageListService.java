package com.message.service;

import com.main.controller.ServiceInterface;
import com.message.dao.MessageDAO;

public class MessageListService implements ServiceInterface {

	@Override
	public Object service(Object id) throws Exception {
		// TODO Auto-generated method stub
		// 생성하고 호출한다.(messageDAO 생성하고 리스트 호출. 호출할 떄 id넘기는거
		return new MessageDAO().list((String) id);
	}

}

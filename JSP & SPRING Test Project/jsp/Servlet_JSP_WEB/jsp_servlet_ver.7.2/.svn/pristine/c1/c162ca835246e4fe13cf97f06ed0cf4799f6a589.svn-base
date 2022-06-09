package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;

public class MessageViewService implements Service {

	private MessageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MessageVO vo = (MessageVO) obj;
		// 읽음 표시한다.
		// 읽지 않은 메시지를 읽음으로 처리하기 위해서 setReaded() 호출한다.
		dao.setReaded(vo);
		
		// 데이터를 가져온다.
		// 생성하고 view() 호출한다.
		return dao.view(vo.getNo());
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

}

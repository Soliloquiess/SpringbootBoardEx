package com.webjjang.message.service;

import com.webjjang.main.controller.ServiceInterface;
import com.webjjang.message.dao.MessageDAO;

public class MessageViewService implements ServiceInterface {

	@Override
	public Object service(Object no) throws Exception {
		// TODO Auto-generated method stub
		// 변수로 저장하는 경우는 2번 이상 사용되는 경우는 꼭 변수로 저장을 해 주세요.
		MessageDAO dao = new MessageDAO();
		// 읽음 표시한다.
		// 생성하고 setReaded() 호출한다.
		dao.setReaded((Long)no);
		
		// 데이터를 가져온다.
		// 생성하고 view() 호출한다.
		return dao.view((Long) no);
	}

}

package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.util.PageObject;

public class MessageListService implements Service {

	private MessageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		PageObject pageObject = (PageObject) obj;
		
		// 전달받은 doa를 호출한다.
		// 전체 데이터 가져오기 - PageObject에 세팅한다.
		long cnt = dao.getTotalRow(pageObject);
		pageObject.setTotalRow(cnt); // 페이지 정보 계산이 된다.
		
		// 메시지 리스트 데이터를 가져와서 전달한다.
		return dao.list(pageObject);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

}

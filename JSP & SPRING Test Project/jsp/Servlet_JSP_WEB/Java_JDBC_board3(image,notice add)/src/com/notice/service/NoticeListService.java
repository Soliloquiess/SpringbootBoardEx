/*
 * 공지 리스트를 해결하기 위한 객체
 * - NoticeDAO 객체를 사용해서 DB에서 데이터를 수집해 온다.
 */
package com.notice.service;

import com.notice.dao.NoticeDAO;
import com.main.controller.Service;

public class NoticeListService implements Service {
	
	NoticeDAO dao = new NoticeDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

}

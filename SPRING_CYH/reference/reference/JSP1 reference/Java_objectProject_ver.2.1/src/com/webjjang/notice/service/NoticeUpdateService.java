/*
 * 공지 DB 수정을 해결하기 위한 객체
 * - NoticeDAO 객체를 사용해서 DB에서 데이터를 수집해 온다.
 */
package com.webjjang.notice.service;

import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.main.controller.Service;

public class NoticeUpdateService implements Service {
	
	NoticeDAO dao = new NoticeDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글수정 - vo 데이터를 넘겨주고 DB에 저장시켜 준다.
		return dao.update((NoticeVO) obj);
	}

}

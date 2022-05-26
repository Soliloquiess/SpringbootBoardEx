/*
 * 공지 글쓰기를 해결하기 위한 객체
 * - NoticeDAO 객체를 사용해서 DB에서 데이터를 저장한다.
 */
package com.notice.service;

import com.notice.dao.NoticeDAO;
import com.notice.vo.NoticeVO;
import com.main.controller.Service;

public class NoticeWriteService implements Service {
	
	NoticeDAO dao = new NoticeDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글쓰기 - vo 데이터를 넘겨주고 DB에 저장시켜 준다.
		return dao.write((NoticeVO) obj);
	}

}

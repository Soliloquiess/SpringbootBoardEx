package com.webjjang.notice.service;

import com.webjjang.notice.dao.NoticeDAO;

public class NoticeDeleteService {

	public int service(long no) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.delete(no);
	}
}

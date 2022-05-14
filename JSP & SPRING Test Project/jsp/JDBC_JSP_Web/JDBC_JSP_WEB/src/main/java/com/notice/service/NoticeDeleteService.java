package com.notice.service;

import com.notice.dao.NoticeDAO;

public class NoticeDeleteService {

	public int service(long no) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.delete(no);
	}
}

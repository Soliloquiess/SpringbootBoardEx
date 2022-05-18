package com.notice.service;

import com.notice.dao.NoticeDAO;
import com.notice.vo.NoticeVO;

public class NoticeViewService {

	public NoticeVO service(long no) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.view(no);
	}
}

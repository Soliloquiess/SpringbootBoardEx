package com.webjjang.notice.service;

import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.vo.NoticeVO;

public class NoticeViewService {

	public NoticeVO service(long no) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.view(no);
	}
}

package com.notice.service;

import com.notice.dao.NoticeDAO;
import com.notice.vo.NoticeVO;

public class NoticeWriteService {

	public int service(NoticeVO vo) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.write(vo);
	}
}

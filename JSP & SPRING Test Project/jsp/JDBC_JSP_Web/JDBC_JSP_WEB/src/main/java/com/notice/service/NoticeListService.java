package com.notice.service;

import java.util.List;

import com.notice.dao.NoticeDAO;
import com.notice.vo.NoticeVO;

public class NoticeListService {

	public List<NoticeVO> service(String pt) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		return dao.list(pt);
	}
	
}

package com.notice.service;

import java.util.List;

import com.notice.dao.NoticeDAO;
import com.notice.vo.NoticeVO;
import com.util.PageObject;

public class NoticeListService {

	// pt = pageObject.period
	public List<NoticeVO> service(PageObject pageObject) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		// 페이지 계산을 위해서 setTotalRow()
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
	
}

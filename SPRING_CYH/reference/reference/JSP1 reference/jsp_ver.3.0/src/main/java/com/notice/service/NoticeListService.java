package com.webjjang.notice.service;

import java.util.List;

import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

public class NoticeListService {

	// pt = pageObject.period
	public List<NoticeVO> service(PageObject pageObject) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		// 페이지 계산을 위해서 setTotalRow()
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
	
}

package com.webjjang.image.service;

import java.util.List;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;

public class ImageListService {

	public List<ImageVO> service(PageObject pageObject) throws Exception{
		ImageDAO dao = new ImageDAO();
		// 페이지 정보 데이터 계산을 한다. - 안하면 데이터를 안 가져옴.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
	
}

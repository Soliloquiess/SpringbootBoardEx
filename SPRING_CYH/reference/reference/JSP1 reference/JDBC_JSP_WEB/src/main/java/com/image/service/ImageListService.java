package com.image.service;

import java.util.List;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;
import com.util.PageObject;

public class ImageListService {

	public List<ImageVO> service(PageObject pageObject) throws Exception{
		ImageDAO dao = new ImageDAO();
		// 페이지 정보 데이터 계산을 한다. - 안하면 데이터를 안 가져옴.
		
		//페이지 정보 계산을 한다. - 안하면 데이터를 안 가져온다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		//pageObject 전체 데이터 가져온걸(getobject로) pageObject의 setTotalRow에 저장해라.
		return dao.list(pageObject);
	}
	
}

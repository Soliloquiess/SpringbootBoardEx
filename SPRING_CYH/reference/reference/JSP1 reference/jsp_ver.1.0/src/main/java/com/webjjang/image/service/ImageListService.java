package com.webjjang.image.service;

import java.util.List;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;

public class ImageListService {

	public List<ImageVO> service() throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.list();
	}
	
}

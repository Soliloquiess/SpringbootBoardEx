package com.image.service;

import java.util.List;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;

public class ImageListService {

	public List<ImageVO> service() throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.list();
	}
	
}

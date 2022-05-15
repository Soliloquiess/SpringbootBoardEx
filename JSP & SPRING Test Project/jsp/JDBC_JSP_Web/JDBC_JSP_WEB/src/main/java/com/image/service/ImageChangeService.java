package com.image.service;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;

public class ImageChangeService {

	public int service(ImageVO vo) throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.imageChange(vo);
	}
}

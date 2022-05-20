package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;

public class ImageUpdateService {

	public int service(ImageVO vo) throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.update(vo);
	}
}

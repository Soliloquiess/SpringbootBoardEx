package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;

public class ImageWriteService {

	public int service(ImageVO vo)  throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.write(vo);
	}
}

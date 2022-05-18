package com.image.service;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;

public class ImageWriteService {

	public int service(ImageVO vo)  throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.write(vo);
	}
}

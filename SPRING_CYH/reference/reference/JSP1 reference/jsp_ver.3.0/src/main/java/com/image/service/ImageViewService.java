package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;

public class ImageViewService {

	public ImageVO service(long no) throws Exception {
		ImageDAO dao = new ImageDAO();
		return dao.view(no);
	}
	
}

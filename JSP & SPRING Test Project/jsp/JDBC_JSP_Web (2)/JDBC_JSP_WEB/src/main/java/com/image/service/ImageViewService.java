package com.image.service;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;

public class ImageViewService {

	public ImageVO service(long no) throws Exception {
		ImageDAO dao = new ImageDAO();
		return dao.view(no);
	}
	
}

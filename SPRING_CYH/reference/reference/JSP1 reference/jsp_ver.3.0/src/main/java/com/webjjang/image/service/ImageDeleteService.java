package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;

public class ImageDeleteService {

	public int service(long no) throws Exception{
		ImageDAO dao = new ImageDAO();
		return dao.delete(no);
	}
	
}

/*
 * 이미지 게시판 DB 수정을 해결하기 위한 객체
 * - ImageDAO 객체를 사용해서 DB에서 데이터를 수집해 온다.
 */
package com.image.service;

import com.image.dao.ImageDAO;
import com.image.vo.ImageVO;
import com.main.controller.Service;

public class ImageUpdateService implements Service {
	
	ImageDAO dao = new ImageDAO();

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 글수정 - vo 데이터를 넘겨주고 DB에 저장시켜 준다.
		return dao.update((ImageVO) obj);
	}

}

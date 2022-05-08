package com.webjjang.image.service;
/*
 * 이미지 게시판 글보기
 * 
 * public ImageVO service()
 */

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.main.controller.Service;

public class ImageViewService implements Service{


	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// 객체 선언
	private ImageDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (ImageDAO) dao;
	}


	@Override
	public ImageVO service(Object obj) throws Exception{
		// DB에서 글번호에 맞는 데이터 가져오기 -> ImageDAO. view(long no)
		Long no = (Long)obj;
		
		// dao 확인
		System.out.println("ImageViewService.service().dao : " + dao);
		
		return dao.view((long) no);
	}
}

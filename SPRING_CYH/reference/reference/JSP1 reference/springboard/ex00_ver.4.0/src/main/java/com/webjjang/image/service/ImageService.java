package com.webjjang.image.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webjjang.image.mapper.ImageMapper;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ImageService {
	
	//자동 DI
	@Inject
	private ImageMapper mapper;

	// 1. 이미지 겔러리 리스트
	public List<ImageVO> list(PageObject pageObject) throws Exception {
		log.info(pageObject);
		// 페이지 처리 정보를 계산한다. - 안하면 데이터가 안나옴.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		return mapper.list(pageObject);
	}
	
	// 2. 이미지 보기
	public ImageVO view(long no) throws Exception {
		log.info(log);
		return mapper.view(no);
	}
	
	// 2-1. 이미지 변경
	public int changeImage(ImageVO vo) throws Exception {
		log.info(vo);
		return mapper.changeImage(vo);
	}
	
	// 3. 이미지 등록
	public int write(ImageVO vo) throws Exception {
		log.info(vo);
		return mapper.write(vo);
	}
	
	// 4. 정보수정
	public int update(ImageVO vo) throws Exception {
		log.info(vo);
		return mapper.update(vo);
	}
	
	// 5. 삭제
	public int delete(long no) throws Exception {
		log.info(no);
		return mapper.delete(no);
	}
	
}

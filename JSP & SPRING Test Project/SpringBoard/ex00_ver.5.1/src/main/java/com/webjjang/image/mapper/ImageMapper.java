package com.webjjang.image.mapper;

import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;

public interface ImageMapper {

	// 1. list
	public List<ImageVO> list(PageObject pageObject) throws Exception;
	
	public long getTotalRow(PageObject pageObject) throws Exception;
	
	// 2. view
	public ImageVO view(long no) throws Exception;
	
	// 2-1. changeImage
	public int changeImage(ImageVO vo) throws Exception;
	
	// 3. write
	public int write(ImageVO vo) throws Exception;
	
	// 4. update - info
	public int update(ImageVO vo) throws Exception;
	
	// 5. delete
	public int delete(long no) throws Exception;
	
}

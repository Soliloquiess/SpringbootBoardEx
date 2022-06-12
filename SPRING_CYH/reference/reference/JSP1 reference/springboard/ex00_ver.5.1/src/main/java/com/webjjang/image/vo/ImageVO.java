package com.webjjang.image.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// lombok 라이브러리가 저장할 당시(컴파일할 때) - 생성자, setter(), getter(), toString()를 자동으로 만들어 준다.
@Data
public class ImageVO {
	
	private long no;
	private String title;
	private String content;
	private String id;
	private String name;
	// @DateTimeFormat(pattern = "yyyy-MM-dd") 
	// 데이터 타입의 형태가 필요한 경우는 날짜 데이터를 입력 받는 경우만 필요하다.
	private Date writeDate;
	private String fileName;
	private MultipartFile image;
	private String deleteImage;

}

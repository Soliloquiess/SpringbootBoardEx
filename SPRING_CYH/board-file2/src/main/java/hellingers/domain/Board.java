package hellingers.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data	
public class Board {

	private int rowNo;
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updDate;
	
	
	private MultipartFile[] file; //파일정보를 배열로 가져옴
}

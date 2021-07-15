package hell.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {	//dto

		private int replyNo;
		private int boardNo;
		private String content;
		private String writer;
		private Date regDate;
}

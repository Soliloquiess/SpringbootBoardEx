package hell.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Boolean enabled;
	private String email;
	private Date regDate;
	private Date updDate;
	
}

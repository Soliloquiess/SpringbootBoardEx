package com.webjjang.member.service;

import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberViewService {

	public MemberVO service(String id) throws Exception {
		// 생성 호출 :Controller - [Service] - DAO
		MemberDAO dao = new MemberDAO();
		return dao.view(id);
	}
	
}

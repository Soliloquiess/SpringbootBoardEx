package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberDeleteService {

	public int service(MemberVO vo) throws Exception {
		// 생성하고 호출
		MemberDAO dao = new MemberDAO();
		return dao.delete(vo);
	}
	
}

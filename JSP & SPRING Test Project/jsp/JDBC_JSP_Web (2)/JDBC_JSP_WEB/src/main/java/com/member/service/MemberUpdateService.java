package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberUpdateService {
	public int service(MemberVO vo) throws Exception {
		//생성 - 호출
		MemberDAO dao = new MemberDAO();
		return dao.update(vo);
	}
}

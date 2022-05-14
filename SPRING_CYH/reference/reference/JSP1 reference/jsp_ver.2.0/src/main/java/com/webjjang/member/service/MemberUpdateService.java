package com.webjjang.member.service;

import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberUpdateService {

	public int service(MemberVO vo) throws Exception{
		// 생성 - 호출
		MemberDAO dao = new MemberDAO();
		return dao.update(vo);
	}
}

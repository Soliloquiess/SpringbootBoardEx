package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberWriteService {
	public int service(MemberVO vo) throws Exception{
		//생성 호출
		MemberDAO dao = new MemberDAO();
		
		return dao.write(vo);
	}
}

package com.member.service;

import com.main.controller.ServiceInterface;
import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberWriteService implements ServiceInterface {

	@Override
	// 넘어오는 obj는 MemberVO 데이터
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new MemberDAO().write((MemberVO) obj);
	}

}

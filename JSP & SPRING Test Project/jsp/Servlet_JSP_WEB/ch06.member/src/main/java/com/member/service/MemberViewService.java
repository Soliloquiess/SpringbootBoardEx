package com.member.service;

import com.main.controller.ServiceInterface;
import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberViewService implements ServiceInterface {

	@Override
	// 넘어오는 obj는 아이디 - String type
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new MemberDAO().view((String) obj);
	}

}

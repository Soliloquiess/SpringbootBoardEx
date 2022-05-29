package com.member.service;

import com.main.controller.ServiceInterface;
import com.main.member.dao.MemberDAO;

public class MemberIDCheckService implements ServiceInterface {

	//넘어오는 데이터 -id - String
	@Override
	public Object service(Object obj) throws Exception {

		return new MemberDAO().idCheck((String)obj);
	}

}

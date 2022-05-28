package com.webjjang.member.service;

import com.webjjang.main.controller.ServiceInterface;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.LoginVO;

public class LogInService implements ServiceInterface{

	// Controller(JSP) - Service - DAO
	// 넘어오는 obj의 타입 - LoginVO
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new MemberDAO().login((LoginVO) obj);
	}


}

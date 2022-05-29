package com.member.service;

import com.main.controller.ServiceInterface;
import com.member.dao.MemberDAO;
import com.member.vo.LoginVO;

public class LogInService implements ServiceInterface{

	// Controller(JSP) - Service - DAO
	// 넘어오는 obj의 타입 - LoginVO
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new MemberDAO().login((LoginVO) obj);	//이게 jsp로 넘어간다.
	}


}

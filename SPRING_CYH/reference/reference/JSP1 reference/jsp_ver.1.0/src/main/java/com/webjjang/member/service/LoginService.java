package com.webjjang.member.service;

import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.LoginVO;

public class LoginService {

	public LoginVO service(LoginVO inVO) throws Exception{
		MemberDAO dao = new MemberDAO();
		return dao.login(inVO);
	}
	
}

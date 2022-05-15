package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.LoginVO;

public class LoginService {

	public LoginVO service(LoginVO inVO) throws Exception{
		MemberDAO dao = new MemberDAO();
		return dao.login(inVO);
	}
	
}

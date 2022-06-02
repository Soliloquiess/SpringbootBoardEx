package com.member.service;

import com.main.controller.Service;
import com.member.dao.LoginDAO;
import com.member.vo.LoginVO;

public class LoginService implements Service {

	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// DAO 에서 처리 - 생성 & 호출
		// 정상적인 처리가 되었다면 LoginVO 객체가 나온다. 그것을 리턴한다.
		return new LoginDAO().login((LoginVO) vo);
	}
	@Override
	public void setDao(Object dao) {
		
	}
	

}

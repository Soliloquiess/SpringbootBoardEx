package com.member.service;

import com.main.controller.ServiceInterface;
import com.member.dao.MemberDAO;

public class MemberListService implements ServiceInterface {

	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// DAO 에서 처리 - 생성 & 호출
		// 정상적인 처리가 되었다면 List<MemberVO> 객체가 나온다. 그것을 리턴한다.
		return new MemberDAO().list();
	}

}

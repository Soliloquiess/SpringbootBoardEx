package com.webjjang.member.service;

import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class ChangePhotoService {

	public int service(MemberVO vo) throws Exception{
		MemberDAO dao = new MemberDAO();
		return dao.changePhoto(vo);
	}
	
}

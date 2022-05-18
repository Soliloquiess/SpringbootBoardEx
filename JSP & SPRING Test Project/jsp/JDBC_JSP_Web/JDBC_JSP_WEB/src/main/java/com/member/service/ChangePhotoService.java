package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class ChangePhotoService {

	public int service(MemberVO vo) throws Exception{
		MemberDAO dao = new MemberDAO();
		return dao.changePhoto(vo);
	}
	
}

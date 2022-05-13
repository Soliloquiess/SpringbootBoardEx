package com.member.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberDeleteService {
	public int service(MemberVO vo) throws Exception{
		//생성호출
		MemberDAO  dao = new MemberDAO();
		return dao.delete(vo);
	}
}

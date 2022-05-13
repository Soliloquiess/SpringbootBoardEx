package com.member.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;
import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberListService {
	public List<MemberVO> service() throws Exception{
		//생성호출
		MemberDAO dao = new MemberDAO();
		return dao.list();
	}
}

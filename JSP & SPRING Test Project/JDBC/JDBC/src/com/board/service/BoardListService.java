package com.board.service;

import java.util.List;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardListService {
	public List<BoardVO> service(){
		//�����ϰ� ȣ���� ����� �����Ѵ� - Controller - service -DAO
		BoardDAO dao = new BoardDAO();
		return dao.list(); //List<BoardVO>���� ó���Ǵ� Ÿ�԰� dao.list���� ó���Ǵ� Ÿ���� ������.
	}
}
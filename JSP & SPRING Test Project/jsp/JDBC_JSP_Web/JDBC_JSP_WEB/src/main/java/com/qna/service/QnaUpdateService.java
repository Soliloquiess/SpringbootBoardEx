package com.qna.service;

import com.qna.dao.QnaDAO;
import com.qna.vo.QnaVO;

public class QnaUpdateService {

	public int service(QnaVO vo) throws Exception{
		// 생성 - 호출
		QnaDAO dao = new QnaDAO();
		return dao.update(vo);
	}
}

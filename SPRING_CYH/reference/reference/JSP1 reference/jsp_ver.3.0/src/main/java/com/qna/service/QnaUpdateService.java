package com.webjjang.qna.service;

import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.vo.QnaVO;

public class QnaUpdateService {

	public int service(QnaVO vo) throws Exception{
		// 생성 - 호출
		QnaDAO dao = new QnaDAO();
		return dao.update(vo);
	}
}

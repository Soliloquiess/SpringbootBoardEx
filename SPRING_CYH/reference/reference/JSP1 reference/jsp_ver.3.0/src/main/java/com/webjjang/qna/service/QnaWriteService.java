package com.webjjang.qna.service;

import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.vo.QnaVO;

public class QnaWriteService {

	public int service(QnaVO vo) throws Exception{
		QnaDAO dao = new QnaDAO();
		return dao.write(vo);
	}
	
}

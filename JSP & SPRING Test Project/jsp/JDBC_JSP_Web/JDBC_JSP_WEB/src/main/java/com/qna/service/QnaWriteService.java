package com.qna.service;

import com.qna.dao.QnaDAO;
import com.qna.vo.QnaVO;

public class QnaWriteService {

	public int service(QnaVO vo) throws Exception{
		QnaDAO dao = new QnaDAO();
		return dao.write(vo);
	}
	
}

package com.qna.service;

import com.qna.dao.QnaDAO;

public class QnaDeleteService {

	public int service(long no) throws Exception {
		// 생성하고 호출
		QnaDAO dao = new QnaDAO();
		return dao.delete(no);
	}
	
}

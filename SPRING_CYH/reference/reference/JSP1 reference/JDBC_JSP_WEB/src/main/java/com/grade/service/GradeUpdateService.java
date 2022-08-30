package com.grade.service;

import com.grade.dao.GradeDAO;
import com.grade.vo.GradeVO;

public class GradeUpdateService {

	public int service(GradeVO vo) throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.update(vo);
	}
}

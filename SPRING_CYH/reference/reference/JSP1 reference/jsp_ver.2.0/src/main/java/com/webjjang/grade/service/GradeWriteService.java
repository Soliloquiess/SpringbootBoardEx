package com.webjjang.grade.service;

import com.webjjang.grade.dao.GradeDAO;
import com.webjjang.grade.vo.GradeVO;

public class GradeWriteService {

	public int service(GradeVO vo) throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.write(vo);
	}
	
}

package com.grade.service;

import java.util.List;

import com.grade.dao.GradeDAO;
import com.grade.vo.GradeVO;

public class GradeListService {

	public List<GradeVO> service() throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.list();
	}
	
}

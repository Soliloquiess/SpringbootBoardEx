package com.webjjang.grade.service;

import com.webjjang.grade.dao.GradeDAO;

public class GradeDeleteService {

	public int service(int gradeNo) throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.delete(gradeNo);
	}
	
}

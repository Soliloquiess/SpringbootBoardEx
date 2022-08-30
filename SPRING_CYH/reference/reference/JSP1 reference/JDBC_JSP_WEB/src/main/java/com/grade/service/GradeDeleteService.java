package com.grade.service;

import com.grade.dao.GradeDAO;

public class GradeDeleteService {

	public int service(int gradeNo) throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.delete(gradeNo);
	}
	
}

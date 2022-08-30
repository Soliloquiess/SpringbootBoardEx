package com.wisenut.study.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisenut.study.dao.AutoCompleteDao;
import com.wisenut.study.model.autoName;
import com.wisenut.study.service.AutoCompleteService;

@Service
public class AutoCompleteServiceImpl implements AutoCompleteService{
	
	@Autowired
	private AutoCompleteDao autoDao;

	@Override
	public List<HashMap<String, String>> selectEnNmList(Map<String, Object> map) {
		System.out.println("###===> serviceimpl : " +autoDao.selectEnNmList(map));
		return autoDao.selectEnNmList(map);
	}

	

}

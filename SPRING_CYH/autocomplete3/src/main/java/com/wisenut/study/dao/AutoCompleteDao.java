package com.wisenut.study.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AutoCompleteDao {
	
	List<HashMap<String, String>> selectEnNmList(Map<String, Object> map);
}

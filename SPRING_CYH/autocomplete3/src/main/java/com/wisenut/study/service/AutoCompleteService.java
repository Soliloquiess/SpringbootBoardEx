package com.wisenut.study.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wisenut.study.model.autoName;


public interface AutoCompleteService {

	List<HashMap<String, String>> selectEnNmList(Map<String, Object> map);
}

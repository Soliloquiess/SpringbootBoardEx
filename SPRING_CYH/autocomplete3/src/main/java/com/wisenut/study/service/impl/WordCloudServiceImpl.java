package com.wisenut.study.service.impl;

import java.util.ArrayList;

import com.wisenut.study.model.AjaxResponse;
import com.wisenut.study.service.WordCloudService;

public class WordCloudServiceImpl implements WordCloudService{

	@Override
	public AjaxResponse getWord() {
		AjaxResponse response = new AjaxResponse();
		ArrayList<String> wordList = new ArrayList<String>();
		return response;
	}



}

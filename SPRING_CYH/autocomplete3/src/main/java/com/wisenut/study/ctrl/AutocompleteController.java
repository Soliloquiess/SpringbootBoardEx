package com.wisenut.study.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisenut.study.model.AjaxResponse;
import com.wisenut.study.model.InData;
import com.wisenut.study.model.OutData;
import com.wisenut.study.service.AutoCompleteService;

@Controller
public class AutocompleteController {
	
	@Autowired
	private AutoCompleteService autoCompleteService;
	
	@RequestMapping(value = "/auto", method = RequestMethod.GET)
	public String autocomplete() {		
		return "autocomplete";
	}

	@RequestMapping(value = "/ajax/auto", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse autocompleteNameList(HttpServletRequest request) {		
		System.out.println("####=-==>param : " + request.getParameter("value").toUpperCase());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", request.getParameter("value").toUpperCase());
		
		List<HashMap<String, String>> nameList = autoCompleteService.selectEnNmList(param);
		
		JSONArray arrayObj = new JSONArray();
		JSONObject jsonObj = null;
		ArrayList<String> resultlist = new ArrayList<String>();
		
		for (HashMap<String, String> str : nameList) {
			String name = str.get("EN_NM");
			resultlist.add(name);
		}
		for (String str : resultlist) {
			jsonObj = new JSONObject(); 
			jsonObj.put("data", str);
			arrayObj.put(jsonObj);
			
		}
		String name = arrayObj.toString();
		System.out.println("#####====>> name : " + name);
		
		AjaxResponse res = new AjaxResponse();
		InData indata = new InData();
		res.setSuccess(true);
		res.setIndata(indata);
		res.setResMsg(name);
		
		return res;
	}
}

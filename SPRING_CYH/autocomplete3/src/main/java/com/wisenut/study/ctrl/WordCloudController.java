package com.wisenut.study.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wordcloud")
public class WordCloudController {

	@RequestMapping("/list")
	public String getWords() {
		return "/wc/list";
	}
}

package com.ccs.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index.do")
public class IndexController {

	@RequestMapping
	public String index(ModelMap model) {
		return "main";
	}
	@RequestMapping(params = "action=head")
	public String head(ModelMap model) {
		System.out.println("dafsfasfas---");
		return "head";
	}
	
	@RequestMapping(params = "action=left")
	public String left(ModelMap model) {
		return "left";
	}
	
}

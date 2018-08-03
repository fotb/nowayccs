package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IHelpTypeBO;
import com.ccs.vo.HelpTypeVO;

@Controller
public class HelpTypeController {
	@Autowired
	private IHelpTypeBO helpTypeBO;

	@RequestMapping(params = "action=list", method = RequestMethod.GET)
	public @ResponseBody List<HelpTypeVO> getList(@RequestParam String parentId) throws UnsupportedEncodingException {
//		List<HelpTypeVO> list = helpTypeBO.getList(parentId);
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return helpTypeBO.getList(parentId);
	}
}

package com.ccs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.bo.IEventBO;
import com.ccs.util.EasyUiTree;
import com.ccs.util.SgptCategoryTreeUtil;
import com.ccs.vo.EventCategoryVO;

@RestController
public class SgptController {
	
	@Autowired
	private IEventBO eventBO;

    @RequestMapping(value = "/sgpt/getcategory", method = RequestMethod.GET, produces="application/json;charset=UTF-8")  
	public @ResponseBody EventCategoryVO getEventCategoryByCode(@RequestParam String p) throws Exception{
		return eventBO.getEventCategory(p);
	}
    
    @RequestMapping(value = "/sgpt/category/tree", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
	public @ResponseBody List<EasyUiTree> getEventCategoryTree() throws Exception{
		List<EventCategoryVO> list = eventBO.getEventCategory();
		SgptCategoryTreeUtil util = new SgptCategoryTreeUtil(list);
		EasyUiTree easyUiTree = util.generateEasyUiTree("0");
		List<EasyUiTree> tree = new ArrayList<EasyUiTree>();
		tree.add(easyUiTree);
		return tree;
	}
}

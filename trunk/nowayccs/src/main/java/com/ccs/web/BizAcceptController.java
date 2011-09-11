package com.ccs.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IDictBO;
import com.ccs.util.Constants;
import com.ccs.util.Utils;
import com.ccs.vo.DictVO;
import com.ccs.web.domain.BizAccept;

@Controller
@RequestMapping("/bizaccept.do")
public class BizAcceptController {

	@Autowired
	private IDictBO dictBO;
	
	@RequestMapping
	public String accept(
			@RequestParam(value = "callNo", required = false) String callNo,
			ModelMap model) {
		BizAccept bizAccept = new BizAccept();
		bizAccept.setCreateTime(Utils.formateDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("callNo", callNo);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept";
	}
}

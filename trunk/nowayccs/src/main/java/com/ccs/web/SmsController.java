package com.ccs.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.ccs.sms.bo.ISmsRecvBO;
//import com.ccs.sms.util.SmsSpringUtil;
//import com.ccs.sms.vo.SmsRecvVO;
import com.ccs.util.PageInfo;

@Controller
@RequestMapping("/sms.do")
public class SmsController {

	@RequestMapping
	public String list(@RequestParam(value = "pageNo", required = false) String pageNo, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if(null == pageNo) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		model.addAttribute("pageInfo", pageInfo);
		
//		ISmsRecvBO smsRecvBO = (ISmsRecvBO) SmsSpringUtil.getBean("smsRecvBO");
//		
//		List<SmsRecvVO> list = smsRecvBO.findByAll();
//		
//		for (SmsRecvVO vo : list) {
//			System.out.println(vo.getOrgTele());
//			System.out.println(vo.getSmsContent());
//		}
		return "sms/list";
	}
}

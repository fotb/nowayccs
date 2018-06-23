package com.ccs.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccs.vo.AppReceiverVO;

@Controller
public class AppReceiverController {

	
    @RequestMapping(value = "/receiver/add", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
   public String add(@RequestBody AppReceiverVO appReceiverVO) {  
//    public ResponseBody add(@PathVariable("id") String advertiserId	) {  
    	System.out.println(appReceiverVO.getHelpAddr());
    	return null;
    } 
}

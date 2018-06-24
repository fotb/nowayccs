package com.ccs.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.util.Response;
import com.ccs.vo.AppReceiverVO;
import com.ccs.web.domain.AppReceiverParam;

@RestController
public class AppReceiverController {

	
	@Autowired
	private IAppReceiverBO appReceiverBO;
	
    @RequestMapping(value = "/receiver/AppReceiverVOadd", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
   public Response add(@RequestBody @Valid AppReceiverParam appReceiverParam) {  
//    public ResponseBody add(@PathVariable("id") String advertiserId	) {  
    	System.out.println(appReceiverParam.getHelpAddr());
    	try {
	    	AppReceiverVO vo = createAppReceVO(appReceiverParam);
	    	
	    	appReceiverBO.create(vo);
	    	
	    	Response res = new Response();
	    	res.success();
	    	return res;
    	} catch (Exception e) {
    		Response res = new Response();
	    	res.failure("error!");
    		return res;
    	}
    }

	private AppReceiverVO createAppReceVO(AppReceiverParam appReceiverParam) {
		AppReceiverVO vo = new AppReceiverVO();
    	vo.setHelpName(appReceiverParam.getHelpName());
    	vo.setHelpMode(appReceiverParam.getHelpMode());
    	vo.setHelpTel(appReceiverParam.getHelpTel());
    	vo.setHelpAddr(appReceiverParam.getHelpAddr());
    	vo.setHelpContent(appReceiverParam.getHelpContent());
    	vo.setHelpType(appReceiverParam.getHelpType());
    	vo.setHelpAddr(appReceiverParam.getHelpArea());
    	vo.setHelpGroup(appReceiverParam.getHelpGroup());
    	vo.setHelpCategory(appReceiverParam.getHelpCategory());
		return vo;
	} 
}

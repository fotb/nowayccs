package com.ccs.web;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.util.Response;
import com.ccs.util.StringUtil;
import com.ccs.vo.AppReceiverVO;
import com.ccs.web.domain.AppReceiverParam;

@RestController

public class AppReceiverController {

	@Autowired
	private IAppReceiverBO appReceiverBO;
	
    @RequestMapping(value = "/api/receiver/add/", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
    @ResponseBody
   public Response add(@RequestBody @Valid AppReceiverParam appReceiverParam, HttpServletRequest request) {  
    	try {
    		String ip = getIpAdrress(request);
	    	AppReceiverVO vo = createAppReceVO(appReceiverParam);
	    	vo.setIp(ip);
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
    	vo.setHelpArea(appReceiverParam.getHelpArea());
    	vo.setHelpGroup(appReceiverParam.getHelpGroup());
		return vo;
	} 
	
	
	private static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtil.isNull(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isNull(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isNull(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isNull(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isNull(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}

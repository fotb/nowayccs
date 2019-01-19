package com.ccs.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IElevBO;
import com.ccs.util.Constants;
import com.ccs.util.StringUtil;
import com.ccs.util.Utils;
import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.BizAccept;
import com.ccs.web.domain.ElevatorDomain;

@Controller
@RequestMapping("/elev.do")
public class ElevatorController {
	
	private static final String CALL_ID = "CALL_ID";
	
	@Autowired
	private IElevBO elevBO;

    //@RequestMapping(value = "/elev/getelev/", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @RequestMapping(params = "action=getelev")
	public @ResponseBody List<ElevatorVO> getElevator(@RequestParam String q) {
		return elevBO.getElevator(q);
	}
    
    
    
	 //@RequestMapping(value = "/elev/bizaccept/save", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @RequestMapping(params = "action=save")
//	@ResponseBody
	//public Response acceptElevatorSave(ElevatorDomain domain, HttpSession session) throws Exception {
    public String acceptElevatorSave(ElevatorDomain domain, ModelMap model, HttpSession session) throws Exception {
		 try {
			 UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
			
			//?? Use the same name of "bizAccept" with ModelAttribute???
			BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
			
//			ElevatorDomain domain = new ElevatorDomain();
			
			Date date = new Date();
			
			InformationVO vo = new InformationVO();
			vo.setCreateTime(Utils.stringToDate(bizAccept.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			vo.setCreator(user.getUserId());
			vo.setHelpAddr(bizAccept.getHelpAddr());
			vo.setHelpArea(bizAccept.getHelpArea());
			vo.setHelpContent(bizAccept.getHelpContent());
			vo.setHelpGroup(StringUtil.emptyToNull(bizAccept.getHelpGroup()));
			vo.setHelpMode(bizAccept.getHelpMode());
			vo.setHelpName(bizAccept.getHelpName());
			final String helpTel = bizAccept.getHelpTel() + (StringUtil.isNull(bizAccept.getOtherTel()) ? "" : "," + bizAccept.getOtherTel());
			vo.setHelpTel(helpTel);
			vo.setHelpType(bizAccept.getHelpType());
			String callId = (String) session.getAttribute(CALL_ID);
			if(!StringUtil.isNull(callId)) {
				vo.setCallId(callId);
				vo.setRecordFlag(Constants.SYS_YESNO_YES);
			} else {
				vo.setRecordFlag(Constants.SYS_YESNO_NO);
			}
			
			vo.setFinishTime(date);
			vo.setStatus(Constants.SYS_INFOMATION_STATES_CLZ);
			
			
			ElevatorVO elevVO = new ElevatorVO();
			elevVO.setUseDept(domain.getUseDept());
			elevVO.setPosition(domain.getPosition());
			elevVO.setServiceName(domain.getServiceName());
			elevVO.setManufacturer(domain.getManufacturer());
			elevVO.setReferNo(domain.getReferNo());
			elevVO.setDeviceId(domain.getDeviceId());
			elevVO.setSerialNumber(domain.getSerialNumber());
			elevVO.setType(domain.getType());
			elevVO.setSurveyDate(domain.getSurveyDate());
			elevVO.setNextSurveyDate(domain.getNextSurveyDate());
			elevVO.setLastHandler(user.getUserId());
			elevVO.setUpdateDT(date);
			
			ElevHelpInfoVO ehiVO = new ElevHelpInfoVO();
			ehiVO.setReportingTime(domain.getReportingTime());
			ehiVO.setReporter(domain.getReporter());
			if("other".equals(domain.getReportPhone())) {
				ehiVO.setReportPhone(domain.getOtherPhone());
			} else {
				ehiVO.setReportPhone(domain.getReportPhone());
			}
			ehiVO.setHelpDept(domain.getHelpDept());
			ehiVO.setDispatchTime(domain.getDispatchTime());
			ehiVO.setStartTime(domain.getStartTime());
			ehiVO.setArriveTime(domain.getArriveTime());
			ehiVO.setFinishTime(domain.getFinishTime());
			ehiVO.setTrapppedPerson(domain.getTrapppedPerson());
			ehiVO.setCasualty(domain.getCasualty());
			ehiVO.setInjuries(domain.getInjuries());
			ehiVO.setDeathToll(domain.getDeathToll());
			ehiVO.setReason(domain.getReason());
			ehiVO.setDealResult(domain.getDealResult());
			ehiVO.setDutyResult(domain.getDutyResult());
			ehiVO.setLastHandler(user.getUserId());
			
			elevBO.addElevHelp(vo, elevVO, ehiVO);
			
			session.setAttribute("bizAccept", null);
//			Response res = new Response();
//	    	res.success();
//	    	return res;
			
//			session.setAttribute("bizAccept", null);
			if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
				return "common/selfclose";
			} else {
				return "redirect:bizaccept.do?action=old";
			}
		 }catch(Exception e) {
/*	    		Response res = new Response();
		    	res.failure("error!");
	    		return res;*/
			 return "redirect:bizaccept.do?action=old";
		 }
	}
}

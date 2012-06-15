package com.ccs.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.ISmsRecvBO;
import com.ccs.bo.ISmsSendBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.SmsRecvVO;
import com.ccs.vo.SmsSendVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.SmsBean;

@Controller
@RequestMapping("/sms.do")
public class SmsController {

	@Autowired
	private ISmsRecvBO smsRecvBO;
	
	@Autowired
	private ISmsSendBO smsSendBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", required = false) String pageNo, ModelMap model, HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		if(null == pageNo) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		model.addAttribute("pageInfo", pageInfo);
		
		String errorMsg = smsRecvBO.RecvSms();
		if(!StringUtil.isNull(errorMsg)) {
			model.addAttribute("remoteError", "Y");
//			return "sms/list";
		}
		
		List<SmsRecvVO> list = smsRecvBO.findByStatus(SmsRecvVO.STATUS_NEW);
		
		model.addAttribute("smsList", list);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(null != userBO.findUserRole(user.getUserId(), Constants.SYS_ROLE_ADMIN)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}

		
		return "sms/list";
	}
	
	@RequestMapping(params = "action=delete")
	public String delete(String smsId, ModelMap model) {
		final SmsRecvVO vo = smsRecvBO.findById(smsId);
		vo.setStatus(SmsRecvVO.STATUS_DELETE);
		vo.setUpdateDt(new Date());
		smsRecvBO.saveOrUpdate(vo);
		return "redirect:sms.do";
	}
	
	
	@RequestMapping(params="action=new")
	public String newSms(ModelMap model) {
		SmsBean bean = new SmsBean();
		model.addAttribute("smsBean", bean);
		return "sms/send";
	}
	
	@RequestMapping(params="action=send")
	public String send(SmsBean smsBean, ModelMap model) {
		SmsSendVO vo = new SmsSendVO();
		vo.setTelNum(smsBean.getPhoneNum());
		vo.setContent(smsBean.getSmsContent());
		vo.setSendDt(new Date());
	
		String errorMsg = smsSendBO.send(vo);
		if("Y".equals(errorMsg)) {
			model.addAttribute("errorMsg", "Y");
			model.addAttribute("smsBean", smsBean);
		} else {
			model.addAttribute("smsBean", new SmsBean());
		}
		return "sms/send";
	}
}

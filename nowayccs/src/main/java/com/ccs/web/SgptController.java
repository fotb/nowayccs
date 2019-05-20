package com.ccs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IEventBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.EasyUiTree;
import com.ccs.util.SgptCategoryTreeUtil;
import com.ccs.util.StringUtil;
import com.ccs.util.Utils;
import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.BizAccept;
import com.ccs.web.domain.EventDomain;


//@RestController
@Controller
@RequestMapping("/sgpt.do")
public class SgptController {
	
	private static Logger log = Logger.getLogger(SgptController.class);
	
	private static final String FORMATE_CREATETIME = "yyyy-MM-dd HH:mm:ss";
	
	private static final String CALL_ID = "CALL_ID";
	
	@Autowired
	private IEventBO eventBO;

//    @RequestMapping(value = "/sgpt/getcategory", method = RequestMethod.GET, produces="application/json;charset=UTF-8")  
	@RequestMapping(params = "action=getcategory")
	public @ResponseBody EventCategoryVO getEventCategoryByCode(@RequestParam String p) throws Exception{
		return eventBO.getEventCategory(p);
	}
    
//    @RequestMapping(value = "/sgpt/category/tree", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    @RequestMapping(params = "action=categorytree")
	public @ResponseBody List<EasyUiTree> getEventCategoryTree() throws Exception{
		List<EventCategoryVO> list = eventBO.getEventCategory();
		SgptCategoryTreeUtil util = new SgptCategoryTreeUtil(list);
		EasyUiTree easyUiTree = util.generateEasyUiTree("0");
		List<EasyUiTree> tree = new ArrayList<EasyUiTree>();
		tree.add(easyUiTree);
		return tree;
	}
    
    
    
//	 @RequestMapping(value = "/sgpt/bizaccept/save", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
//	 @ResponseBody
    @RequestMapping(params = "action=save")
	public String acceptSgptSave(EventDomain domain, ModelMap model, HttpSession session) throws Exception {
		 try {
			 UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
			
			//?? Use the same name of "bizAccept" with ModelAttribute???
			BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
			
			Date date = new Date();
			
			InformationVO vo = new InformationVO();
			vo.setCreateTime(Utils.stringToDate(bizAccept.getCreateTime(), FORMATE_CREATETIME));
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
			
			EventVO eventVO = new EventVO();
			eventVO.setEventSubject(domain.getEventSubject());
			eventVO.setEventDate(domain.getEventDate());
			eventVO.setEventLocation(domain.getEventLocation());
			eventVO.setEventContent(domain.getEventContent());
			eventVO.setEventLevel(domain.getEventLevel());
			eventVO.setEventSource(EventVO.EVENT_SOURCE_HOTCALL); //热线电话
			eventVO.setIsImpPlase(domain.getIsImpPlase());
			eventVO.setRelatePeopleCount(domain.getRelatePeopleCount());
			
			eventVO.setFirstCategoryId(domain.getFirstCategoryId());
			eventVO.setSecondCategoryId(domain.getFirstCategoryId());  //事件编码
			
			eventVO.setStatus(EventVO.EVENT_STATUS_10);
			eventVO.setMobile(domain.getMobile());
			eventVO.setOrganizationId(EventVO.EVENT_ORG_ID);
	//		eventVO.setOrginternalCode(EventVO.EVENT_ORG_CODE);
			eventVO.setObjName(domain.getObjName());
			
			//事件编号：
			String serialNum = EventVO.EVENT_ORG_ID + DateUtil.format(date, "yyMMdd") + domain.getFirstCategoryId() + genCode();
			eventVO.setSerialNumber(serialNum);
			
			eventBO.acceptSGPT(vo, eventVO);
			
			session.setAttribute("bizAccept", null);
//			Response res = new Response();
//	    	res.success();
//	    	return res;
			
			if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
				return "common/selfclose";
			} else {
				return "redirect:bizaccept.do?action=old";
			}
		 }catch(Exception e) {
//	    		Response res = new Response();
//		    	res.failure("error!");
//	    		return res;
			 log.error(e.getMessage());
			 return "redirect:bizaccept.do?action=old";
		 }
	}
	 
	 @RequestMapping("/sgpg.do?action=finish")
	 public String finish() {
		 return "common/selfclose";
	 }
	 
	 
	 
	 private String genCode() {
		// 字符串
	        String string = "";
	        // 循环得到10个字母
	        for (int i = 0; i < 7; i++) {
	            // 得到随机字母
	            char c = (char) ((Math.random() * 26) + 'A');
	            // 拼接成字符串
	            string += (c + "");
	        }
	       return string;
	 }
}

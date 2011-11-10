package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ccs.bo.IBizAcceptBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.JQGridFormatterUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.util.Utils;
import com.ccs.vo.DictVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.ReferInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.BizAccept;
import com.ccs.web.domain.InfoBean;

@Controller
@RequestMapping("/bizaccept.do")
@SessionAttributes("bizAccept")
public class BizAcceptController {
	private static final String FORMATE_CREATETIME = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IBizAcceptBO bizAcceptBO;
	
	@RequestMapping
	public String accept(@RequestParam(value = "callNo", required = false) String callNo, 
			@RequestParam(value = "flag", required = false) String flag, HttpSession session, 
			ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = new BizAccept();
		bizAccept.setHelpTel(callNo);
		bizAccept.setCreator(userVO.getUserId());
		bizAccept.setCreateTime(Utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		bizAccept.setPopupFlag(flag);
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept";
	}
	
	
	@RequestMapping(params = "action=life")
	public String acceptLife(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizAccept.setCreator(user.getUserName());
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		
		return "bizaccept/acceptlife";
	}
	
	@RequestMapping(params = "action=lifesave")
	public String acceptLifeSave(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model, SessionStatus status) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		List<InformationVO> list = new ArrayList<InformationVO>();
		list.add(getInformationVO(bizAccept, user, "1"));
		if(!StringUtil.isNull(bizAccept.getHelpContent2())) {
			list.add(getInformationVO(bizAccept, user, "2"));
		}
		if(!StringUtil.isNull(bizAccept.getHelpContent3())) {
			list.add(getInformationVO(bizAccept, user, "3"));
		}
		bizAcceptBO.acceptLife(list);
		
		status.setComplete();
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do";
		}
	}


	private InformationVO getInformationVO(BizAccept bizAccept, UserVO user, String index) {
		InformationVO vo = new InformationVO();
		vo.setCreateTime(Utils.stringToDate(bizAccept.getCreateTime(), FORMATE_CREATETIME));
		vo.setCreator(user.getUserId());
		vo.setHelpAddr(bizAccept.getHelpAddr());
		vo.setHelpArea(bizAccept.getHelpArea());
		if("1".equals(index)) {
			vo.setHelpContent(bizAccept.getHelpContent());
		} else if("2".equals(index)){
			vo.setHelpContent(bizAccept.getHelpContent2());
		} else if("3".equals(index)) {
			vo.setHelpContent(bizAccept.getHelpContent3());
		}
		vo.setHelpGroup(StringUtil.emptyToNull(bizAccept.getHelpGroup()));
		vo.setHelpMode(bizAccept.getHelpMode());
		vo.setHelpName(bizAccept.getHelpName());
		vo.setHelpTel(bizAccept.getHelpTel());
		vo.setHelpType(bizAccept.getHelpType());
		return vo;
	}
	
	@RequestMapping(params = "action=affair")
	public String acceptAffair(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizAccept.setCreator(user.getUserName());
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptaffair";
	}
	
	@RequestMapping(params = "action=affairsave")
	public String acceptAffairSave(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model, SessionStatus status) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		InformationVO vo = getInformationVO(bizAccept, user, "1");
		vo.setAffairAcceptor(bizAccept.getHandAcceptor());
		
		bizAcceptBO.acceptAffair(vo);
		
//		status.setComplete();
		return "redirect:bizaccept.do";
	}
	
	@RequestMapping(params = "action=refer")
	public String acceptRefer(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizAccept.setCreator(user.getUserName());
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptrefer";
	}
	
	@RequestMapping(params = "action=refersave")
	public String acceptReferSave(@ModelAttribute("bizAccept") BizAccept bizAccept, ModelMap model, SessionStatus status) {
		InformationVO vo = new InformationVO();
		vo.setCreateTime(Utils.stringToDate(bizAccept.getCreateTime(), FORMATE_CREATETIME));
		vo.setCreator(bizAccept.getCreator());
		vo.setHelpAddr(bizAccept.getHelpAddr());
		vo.setHelpArea(bizAccept.getHelpArea());
		vo.setHelpContent(bizAccept.getHelpContent());
		vo.setHelpGroup(StringUtil.emptyToNull(bizAccept.getHelpGroup()));
		vo.setHelpMode(bizAccept.getHelpMode());
		vo.setHelpName(bizAccept.getHelpName());
		vo.setHelpTel(bizAccept.getHelpTel());
		vo.setHelpType(bizAccept.getHelpType());
		
		ReferInformationVO referInfoVO = new ReferInformationVO();
		referInfoVO.setResult(bizAccept.getResult());
		referInfoVO.setDealer(bizAccept.getCreator());
		referInfoVO.setDealTime(new Date());
		
		bizAcceptBO.acceptRefer(vo, referInfoVO);
		
		status.setComplete();
		return "redirect:bizaccept.do";
	}
	
	@RequestMapping(params = "action=helphist", method = RequestMethod.GET)
	public @ResponseBody String getHelpHist(@RequestParam(value = "callNo", required = false) String callNo, 
			@RequestParam("_search") String _search,
			@RequestParam("nd") String nd,
			@RequestParam("rows") int rows,
			@RequestParam("page") int page,
			@RequestParam("sidx") String sidx,
			@RequestParam("sord") String sord) throws UnsupportedEncodingException {
		List<InformationVO> list = new ArrayList<InformationVO>();
		List<InfoBean> infoList = new ArrayList<InfoBean>();
		PageInfo pageInfo = new PageInfo();
		if(!StringUtil.isNull(callNo)) {
			pageInfo.setCurrentPage(page);
			pageInfo.setRows(rows);
			list = bizAcceptBO.findByInfoByTel(callNo, pageInfo);
		}
		
		Map<String, String> qzfsMap = dictBO.getDict(Constants.DICT_DICTTYPE_QZFS);
		Map<String, String> slrqMap = dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ);
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			InfoBean infoBean = new InfoBean();
			infoBean.setCreateTime(infoVO.getCreateTime());
			infoBean.setCreator(infoVO.getCreator());
			infoBean.setFinishTime(infoVO.getFinishTime());
			infoBean.setHelpAddr(infoVO.getHelpAddr());
			infoBean.setHelpArea(infoVO.getHelpArea());
			infoBean.setHelpContent(infoVO.getHelpContent());
			infoBean.setHelpGroup(slrqMap.get(infoVO.getHelpGroup()));
			infoBean.setHelpMode(qzfsMap.get(infoVO.getHelpMode()));
			infoBean.setHelpName(infoVO.getHelpName());
			infoBean.setHelpTel(infoVO.getHelpTel());
			infoBean.setHelpType(Constants.INFOMATION_HELPTYPE_HASHMAP.get(infoVO.getHelpType()));
			infoBean.setInfoId(infoVO.getInfoId());
			infoBean.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
			
			infoList.add(infoBean);
			
		}
		
		List<String> propList = new ArrayList<String>();
		propList.add("helpName");
		propList.add("createTime");
		propList.add("helpAddr");
		propList.add("helpContent");
		propList.add("helpType");
		propList.add("creator");
		propList.add("finishTime");
		propList.add("status");
		
		return JQGridFormatterUtil.getJSON(page, pageInfo.getTotalRecords(), rows, infoList, propList, "infoId");
	}
}

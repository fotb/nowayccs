package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.bo.IBizAcceptBO;
import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IPowerInformationBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.EasyUiTree;
import com.ccs.util.LifeCategoryTreeUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.util.Utils;
import com.ccs.vo.AppReceiverVO;
import com.ccs.vo.BaseEntity;
import com.ccs.vo.DictVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeCategoryVO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.ReferInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.BizAccept;
import com.ccs.web.domain.InfoBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/bizaccept.do")
//@SessionAttributes("bizAccept")
public class BizAcceptController {
	
	private static final Logger logger = Logger.getLogger(BizAcceptController.class);
	
	private static final String FORMATE_CREATETIME = "yyyy-MM-dd HH:mm:ss";
	
	private static final String CALL_ID = "CALL_ID";
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IBizAcceptBO bizAcceptBO;
	
	@Autowired
	private IPowerInformationBO powerInfoBO;
	
	@Autowired
	private IAppReceiverBO appReceiverBO;
	
	@Autowired
	private IBizLifeBO bizLifeBO;
	
	
	//http://$IP/?ANI=18958126977&logName=Administrator&password=&agentId =001&groupId=GD-SA& recordFile =&bpoHisId=&orgNumber=
	
	@RequestMapping
	public String accept(@RequestParam(value = "ANI", required = false) String callNo, 
			@RequestParam(value = "logName", required = false) String logName, 
			@RequestParam(value="password", required = false) String password,
			@RequestParam(value="agentId", required = false) String agentId,
			@RequestParam(value="groupId", required = false) String groupId,
			@RequestParam(value="recordFile", required = false) String recordFile,
			@RequestParam(value="bpoHisId", required = false) String bpoHisId,
			@RequestParam(value="orgNumber", required = false) String orgNumber,
			HttpSession session, 
			ModelMap model) {
		
		/*System.out.println(
				"ANI:" + callNo + "---logName:" + logName + "---password:" + password + "---agentId: " + agentId + "---groupId:"
						+ groupId + "---recordFile: " + recordFile + "---bpoHisId: " + bpoHisId + "---orgNumber: " + orgNumber);*/
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = new BizAccept();
		bizAccept.setHelpTel(callNo);
		bizAccept.setCreator(userVO.getUserId());
		bizAccept.setPopupFlag(Constants.SYS_YESNO_YES);
		bizAccept.setCreateTime(Utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		bizAccept.setHelpMode(qzfs);
//		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept2";
	}
	
	
	
		
	@RequestMapping(params = "action=old")
	public String acceptOld(@RequestParam(value = "callNo", required = false) String callNo, 
			@RequestParam(value = "flag", required = false) String flag, 
			@RequestParam(value="qzfs", required = false) String qzfs, HttpSession session, 
			ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = new BizAccept();
		bizAccept.setHelpTel(callNo);
		bizAccept.setCreator(userVO.getUserId());
		bizAccept.setPopupFlag(flag);
		bizAccept.setCreateTime(Utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		bizAccept.setHelpMode(qzfs);
//		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept2";
	}

	
	@RequestMapping(params = "action=app")
	public String acceptApp(@RequestParam(value = "appInfoId", required = false) String appInfoId, 
			@RequestParam(value = "flag", required = false) String flag, HttpSession session, 
			ModelMap model) throws Exception {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		AppReceiverVO appReceiverVO = appReceiverBO.findById(appInfoId);
		BizAccept bizAccept = new BizAccept();
		bizAccept.setAppInfoId(appInfoId);
		bizAccept.setHelpTel(appReceiverVO.getHelpTel());
		bizAccept.setHelpAddr(appReceiverVO.getHelpAddr());
		bizAccept.setHelpContent(appReceiverVO.getHelpContent());
		bizAccept.setHelpMode(appReceiverVO.getHelpMode());
		bizAccept.setCreator(userVO.getUserId());
		bizAccept.setHelpGroup(appReceiverVO.getHelpGroup());
		bizAccept.setPopupFlag(flag);
		bizAccept.setCreateTime(Utils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		bizAccept.setHelpName(appReceiverVO.getHelpName());
		bizAccept.setHelpGroup(appReceiverVO.getHelpGroup());
		bizAccept.setHelpType(Constants.INFOMATION_HELPTYPE_LIFE);
		
		
//		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept2";
	}
	
	@RequestMapping(params = "action=back")
	public String backAccept(HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");

		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept2";
	}
	
	@RequestMapping(params = "action=life")
	public String acceptLife(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		
		return "bizaccept/acceptlife";
	}
	
	@RequestMapping(params = "action=lifesave")
	public String acceptLifeSave(HttpSession session, ModelMap model) throws Exception{
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
		
		List<InformationVO> list = new ArrayList<InformationVO>();
		list.add(getInformationVO(bizAccept, user, "1", session));
		if(!StringUtil.isNull(bizAccept.getHelpContent2())) {
			list.add(getInformationVO(bizAccept, user, "2", session));
		}
		if(!StringUtil.isNull(bizAccept.getHelpContent3())) {
			list.add(getInformationVO(bizAccept, user, "3", session));
		}
		if(!StringUtil.isNull(bizAccept.getHelpContent4())) {
			list.add(getInformationVO(bizAccept, user, "4", session));
		}
		if(StringUtil.isNotEmpty(bizAccept.getAppInfoId())) {
			bizAcceptBO.acceptAppLife(list, bizAccept.getAppInfoId());
		} else {
			bizAcceptBO.acceptLife(list);
		}
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do?action=old";
		}
	}


	private InformationVO getInformationVO(BizAccept bizAccept, UserVO user, String index, HttpSession session) {
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
		} else if("4".equals(index)) {
			vo.setHelpContent(bizAccept.getHelpContent4());
		}
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
		vo.setHelpCategory(bizAccept.getHelpCategory());
		return vo;
	}
	
	@RequestMapping(params = "action=affair")
	public String acceptAffair(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptaffair";
	}
	
	@RequestMapping(params = "action=affairsave")
	public String acceptAffairSave(@ModelAttribute("bizAccept") BizAccept bindBizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
		bizAccept.setHandAcceptor(bindBizAccept.getHandAcceptor());
		
		InformationVO vo = getInformationVO(bizAccept, user, "1", session);
		vo.setAffairAcceptor(bizAccept.getHandAcceptor());
		
		bizAcceptBO.acceptAffair(vo);
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do?action=old";
		}
	}
	
	@RequestMapping(params = "action=refer")
	public String acceptRefer(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptrefer";
	}
	
	@RequestMapping(params = "action=refersave")
	public String acceptReferSave(@ModelAttribute("bizAccept") BizAccept bindBizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		//?? Use the same name of "bizAccept" with ModelAttribute???
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
		bizAccept.setResult(bindBizAccept.getResult());
		
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
		
		ReferInformationVO referInfoVO = new ReferInformationVO();
		referInfoVO.setResult(bizAccept.getResult());
		referInfoVO.setDealer(bizAccept.getCreator());
		referInfoVO.setDealTime(new Date());
		
		bizAcceptBO.acceptRefer(vo, referInfoVO);
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do?action=old";
		}
	}
	
	
	
	@RequestMapping(params = "action=helphist", method = RequestMethod.GET)
	public @ResponseBody JSONObject getHelpHist(@RequestParam(value = "callNo", required = false) String callNo, 
//			@RequestParam("_search") String _search,
//			@RequestParam("nd") String nd,
			@RequestParam("rows") int rows,
			@RequestParam("page") int page
//			@RequestParam("sidx") String sidx,
//			@RequestParam("sord") String sord
			) throws UnsupportedEncodingException {
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
		
		Map<String, UserVO> userMap = userBO.findAll();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			InfoBean infoBean = new InfoBean();
			infoBean.setCreateTime(DateUtil.format(infoVO.getCreateTime(), FORMATE_CREATETIME));
			infoBean.setCreator(userMap.get(infoVO.getCreator()).getUserName());
			infoBean.setFinishTime(DateUtil.format(infoVO.getFinishTime(), FORMATE_CREATETIME));
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
		propList.add("infoId");
		
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("total", pageInfo.getTotalRecords());

		JSONArray jsonArray = JSONArray.fromObject(infoList);

		jsonObj.put("rows", jsonArray.toString());
//		jsonObj.put("footer", JSONArray.fromObject(lpsTreeBean.getFooter()).toString());
		return jsonObj;
//		return JQGridFormatterUtil.getJSON(page, pageInfo.getTotalRecords(), rows, infoList, propList, "infoId");
	}
	
	@RequestMapping(params = "action=recordfile", method = RequestMethod.POST)
	public @ResponseBody void postRecordFileName(String callId, HttpSession session) {
		session.setAttribute(CALL_ID, callId);
		logger.info("Got callId: " + callId);
	}
	
	
	@RequestMapping(params = "action=power")
	public String acceptPower(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptpower";
	}
	
	
	@RequestMapping(params = "action=pslist", method = RequestMethod.GET)
	public @ResponseBody JSONObject buildLPSTree(@RequestParam(value="areaId", required=false) String areaId, @RequestParam(value="areaSubId", required=false) String areaSubId) throws Exception {
		List<PowerStaffVO> psVOList = powerInfoBO.findByAreaSubId(areaSubId);
		//		JSONArray jsonObj = JSONArray.fromObject(lpsTreeBean);
//		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", psVOList.size());
		
		
		JSONArray jsonArray = JSONArray.fromObject(psVOList);
		
		jsonObj.put("rows", jsonArray.toString());
//		System.out.println("json: " + jsonObj.toString());
		return jsonObj;
	}
	
	
	
	@RequestMapping(params = "action=powersave")
	public String acceptPowerSave(@ModelAttribute("bizAccept") BizAccept bindBizAccept, @RequestParam(value="powerStaffId") String powerStaffId,  @RequestParam(value="areaSubId1") String areaSubId, HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		//?? Use the same name of "bizAccept" with ModelAttribute???
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
		bizAccept.setResult(bindBizAccept.getResult());
		
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
		
		PowerInformationVO piVO = new PowerInformationVO();
		piVO.setCreateTime(date);
		piVO.setDeleteFlag(BaseEntity.DELETE_FLAG_NO);
		piVO.setLastHandler(user.getUserId());
		piVO.setPowerStaffId(powerStaffId);
		piVO.setAreaSubId(areaSubId);
		piVO.setRemark("");
		piVO.setUpdateDT(date);
		
		bizAcceptBO.acceptPower(vo, piVO);
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do?action=old";
		}
	}
	
	
	@RequestMapping(params = "action=elevator")
	public String acceptElevator(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
//		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
//		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
//		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
//		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
//		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptelevator";
	}
	
	@RequestMapping(params = "action=sgpt")
	public String acceptSgpt(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		bizAccept.setCreator(user.getUserName());
		session.setAttribute("bizAccept", bizAccept);
		
		model.addAttribute("bizAccept", bizAccept);
//		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
//		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
//		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
//		model.addAttribute("slrqMap", dictBO.getDict(Constants.DICT_DICTTYPE_SLRQ));
//		model.addAttribute("userList", userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SWYWCL));
		return "bizaccept/acceptsgpt";
	}
	
	
	//@RequestMapping(params = "action=sgptsave")
	public String acceptSgptSave(@RequestBody EventVO eventVO, HttpSession session, ModelMap model) {
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
		
		PowerInformationVO piVO = new PowerInformationVO();
		piVO.setCreateTime(date);
		
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do?action=old";
		}
	}
	
	@RequestMapping(params = "action=selfcolse")
	 public String finish() {
		 return "common/selfclose";
	 }
	
	
	
    @RequestMapping(params = "action=lifecategorytree")
	public @ResponseBody List<EasyUiTree> getLifeCategoryTree() throws Exception{
		List<LifeCategoryVO> list = bizLifeBO.getLifeCategory();
		LifeCategoryTreeUtil util = new LifeCategoryTreeUtil(list);
		EasyUiTree easyUiTree = util.generateEasyUiTree("0");
		List<EasyUiTree> tree = new ArrayList<EasyUiTree>();
		tree.add(easyUiTree);
		return tree;
	}
    
}

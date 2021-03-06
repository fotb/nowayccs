package com.ccs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bean.AffairInfoReportDTO;
import com.ccs.bean.AffairInfoSearchBean;
import com.ccs.bean.InfoReportDTO;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoReportDTO;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.bean.TrafficSearchBean;
import com.ccs.bean.UserTrafficBean;
import com.ccs.bean.UserTrafficDTO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IInfoReportBO;
import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.HelpCountByPhoneBean;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.HelpCountByPhoneSearchBean;
import com.ccs.web.domain.PowerInfoListBean;
import com.ccs.web.domain.ShsForm;

@Controller
@RequestMapping("/inforeport.do")
public class InfoReportController {

	@Autowired
	private IInfoReportBO infoReportBO;
	
	@Autowired
	private IUserBO userBO;

	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private ILonelyFamilyBO lfBO;
	
	@RequestMapping
	public String list(
			@ModelAttribute("infoSearchBean") InfoSearchBean infoSearchBean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
//		long startTime = System.currentTimeMillis();
		List<InformationVO> list = infoReportBO.findByParams(infoSearchBean,
				pageInfo);
		
//		System.out.println("Tims spend: " + String.valueOf(System.currentTimeMillis() - startTime));
		
		Map<String, UserVO> userMap = userBO.findAll();
		Map<String, String> dictMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
		Map<String, String> resultMap = infoReportBO.getResult(list);
		
		List<InfoReportDTO> dtoList = new ArrayList<InfoReportDTO>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			InfoReportDTO dto = new InfoReportDTO();
			dto.setInfoId(infoVO.getInfoId());
			final String result = resultMap.get(infoVO.getInfoId());
			
			dto.setCallResult(null == result ? "" : dictMap.get(result));
			dto.setCreateTime(infoVO.getCreateTime());
			dto.setCreator(userMap.get(infoVO.getCreator()).getUserName());
			dto.setHelpContent(infoVO.getHelpContent());
			dto.setHelpName(infoVO.getHelpName());
			dto.setHelpTel(infoVO.getHelpTel());
			dto.setHelpType(infoVO.getHelpType());
			dto.setHelpTypeValue(Constants.INFOMATION_HELPTYPE_HASHMAP.get(infoVO.getHelpType()));
			dto.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
			dtoList.add(dto);
		}
		
		model.addAttribute("dtoList", dtoList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("infoSearchBean", infoSearchBean);
		
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		model.addAttribute("helpAreaList", dictBO.findByType(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqList", dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("users", userBO.findAllOnJob());
		model.addAttribute("qzfsList", dictBO.findByType(Constants.DICT_DICTTYPE_QZFS));
		return "inforeport/infolist";
	}
	
	@RequestMapping(params = "action=infocount", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> getInfoCount(@RequestParam String creator, String helpType,
			String helpArea, String helpGroup, String startDt, String endDt, String helpContent)
			throws UnsupportedEncodingException {
		InfoSearchBean infoSearchBean = new InfoSearchBean();
		infoSearchBean.setCreator(creator);
		infoSearchBean.setHelpType(helpType);
		infoSearchBean.setHelpArea(helpArea);
		infoSearchBean.setHelpGroup(helpGroup);
		infoSearchBean.setStartDt(startDt);
		infoSearchBean.setEndDt(endDt);
		infoSearchBean.setHelpContent(helpContent);
		
		Map<String, String> map = new HashMap<String, String>();
		
		//for lonelyhelp start
		ShsForm shsForm = new ShsForm();
		shsForm.setStartDt(startDt);
		shsForm.setEndDt(endDt);
		int jdjtcount = lfBO.countSpecialHelp(shsForm);
		
		shsForm.setStartDt("");
		int jdjt = lfBO.countSpecialHelp(shsForm);
		
		map.put("jdjtcount", String.valueOf(jdjtcount));
		map.put("jdjt", String.valueOf(jdjt));
//		for lonelyhelp end
		
		
		if(StringUtil.isNull(helpType)) {
			infoSearchBean.setHelpType(Constants.INFOMATION_HELPTYPE_LIFE);
			int lifeCount = infoReportBO.getCountByParams(infoSearchBean);
			map.put("lifeCount", String.valueOf(lifeCount));
			
			infoSearchBean.setHelpType(Constants.INFOMATION_HELPTYPE_AFFAIR);
			int affairCount = infoReportBO.getCountByParams(infoSearchBean);
			map.put("affairCount", String.valueOf(affairCount));

			infoSearchBean.setHelpType(Constants.INFOMATION_HELPTYPE_REFER);
			int referCount = infoReportBO.getCountByParams(infoSearchBean);
			map.put("referCount", String.valueOf(referCount));
			
			infoSearchBean.setHelpType(Constants.INFOMATION_HELPTYPE_FERTILITY);
			int fertilityCount = infoReportBO.getCountByParams(infoSearchBean);
			map.put("fertilityCount", String.valueOf(fertilityCount));
			
			infoSearchBean.setHelpType(Constants.INFOMATION_HELPTYPE_POWER);
			int powerCount = infoReportBO.getCountByParams(infoSearchBean);
			map.put("powerCount", String.valueOf(powerCount));
			
			map.put("count", String.valueOf(lifeCount + affairCount + referCount + fertilityCount + jdjtcount + powerCount));
		} else {
			int count = infoReportBO.getCountByParams(infoSearchBean);
			map.put("count", String.valueOf(count + jdjtcount));
			if(helpType.equals(Constants.INFOMATION_HELPTYPE_LIFE)) {
				map.put("lifeCount", String.valueOf(count));
			} else {
				map.put("lifeCount", "0");
			}
			if(helpType.equals(Constants.INFOMATION_HELPTYPE_AFFAIR)) {
				map.put("affairCount", String.valueOf(count));
			} else {
				map.put("affairCount", "0");
			}
			if(helpType.equals(Constants.INFOMATION_HELPTYPE_REFER)) {
				map.put("referCount", String.valueOf(count));
			} else {
				map.put("referCount", "0");
			}
			if(helpType.equals(Constants.INFOMATION_HELPTYPE_FERTILITY)) {
				map.put("fertilityCount", String.valueOf(count));
			} if(helpType.equals(Constants.INFOMATION_HELPTYPE_POWER)) {
				map.put("powerCount", String.valueOf(count));
			}else {
				map.put("fertilityCount", "0");
			}
		}
		
		int life = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_LIFE);
		int affair = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_AFFAIR);
		int refer = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_REFER);
		int fertility = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_FERTILITY);
		int power = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_POWER);

		map.put("life", String.valueOf(life));
		map.put("affair", String.valueOf(affair));
		map.put("refer", String.valueOf(refer));
		map.put("fertility", String.valueOf(fertility));
		map.put("power", String.valueOf(power));
		

		
		
		
		map.put("total", String.valueOf(life + affair + refer + fertility + power + jdjt));
		
		
//		JSONObject jsonObject = JSONObject.fromObject(map);
		return map;
	}
	
	@RequestMapping(params = "action=lifeinforeport")
	public String lifeInfoReport(
			@ModelAttribute("lifeInfoSearchBean") LifeInfoSearchBean bean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		List<InformationVO> list = infoReportBO.findLifeInfoByParams(bean,
				pageInfo);
		Map<String, UserVO> userMap = userBO.findAll();
		Map<String, String> dictMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
		List<String> infoIds = new ArrayList<String>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			infoIds.add(infoVO.getInfoId());
		}
		
		Map<String, VolunteerVO> vltMap = volunteerBO.findAll2Map();
		Map<String, EntpriseVO> entMap = entpriseBO.findAll2Map();
		
		Map<String, LifeInformationVO> lifeInfoMap = infoReportBO.findLifeInfoByInfoIds(infoIds);
		List<LifeInfoReportDTO> dtoList = new ArrayList<LifeInfoReportDTO>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			LifeInfoReportDTO dto = new LifeInfoReportDTO();
			dto.setInfoId(infoVO.getInfoId());
			final LifeInformationVO	lifeInfoVO = lifeInfoMap.get(infoVO.getInfoId());
			dto.setCallResult(null == lifeInfoVO.getHelpApprove() ? "" : dictMap.get(lifeInfoVO.getHelpApprove()));
			dto.setCreateTime(infoVO.getCreateTime());
			dto.setCreator(userMap.get(infoVO.getCreator()).getUserName());
			dto.setHelpContent(infoVO.getHelpContent());
			dto.setHelpName(infoVO.getHelpName());
			dto.setHelpTel(infoVO.getHelpTel());
			dto.setReceiverType(Constants.LIFEINFOMATION_RECEIVETYPE_HASHMAP.get(lifeInfoVO.getReceiverType()));
			if(!StringUtil.isNull(lifeInfoVO.getReceiverType())) {
				if(Constants.LIFEINFOMATION_RECEIVETYPE_QY.equals(lifeInfoVO.getReceiverType())) {
					EntpriseVO entVO = entMap.get(lifeInfoVO.getReceiverId());
					if(null != entVO) {
						dto.setReceiver(entVO.getEntpriseName());
					} else {
						dto.setReceiver("---找不到服务企业!服务企业可能已删除.---");
					}
				} else {
					VolunteerVO vltVO = vltMap.get(lifeInfoVO.getReceiverId());
					if(null != vltVO) {
						dto.setReceiver(vltVO.getVolunteerName());
					} else {
						dto.setReceiver("---找不到服务者!服务者可能已删除.---");
					}
				}
			}
			dto.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
			dtoList.add(dto);
		}
		
		model.addAttribute("dtoList", dtoList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("lifeInfoSearchBean", bean);
		
		model.addAttribute("receiverTypeMap", Constants.LIFEINFOMATION_RECEIVETYPE_HASHMAP);
		model.addAttribute("helpAreaList", dictBO.findByType(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("slrqList", dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ));
		model.addAttribute("mydList", dictBO.findByType(Constants.DICT_DICTTYPE_MYD));
		model.addAttribute("users", userBO.findAllOnJob());
		return "inforeport/lifeinfolist";
	}
	
	@RequestMapping(params = "action=lifeinfocount", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String>  getLifeInfoCount(@RequestParam String receiverType, String keyWords, 
			String startDt, String endDt, String helpArea, String creator)
			throws UnsupportedEncodingException {
		LifeInfoSearchBean bean = new LifeInfoSearchBean();
		bean.setReceiverType(receiverType);
		bean.setKeyWords(keyWords);
		bean.setStartDt(startDt);
		bean.setEndDt(endDt);
		bean.setHelpArea(helpArea);
		bean.setCreator(creator);
		
		Map<String, String> map = new HashMap<String, String>();
		int total = infoReportBO.getLifeCount(bean);
		map.put("total", String.valueOf(total));
		
		bean.setStatus(Constants.SYS_INFOMATION_STATES_YQX);
		int cancelTotal = infoReportBO.getLifeCount(bean);
		map.put("canceltotal", String.valueOf(cancelTotal));

		bean.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		int finishtotal = infoReportBO.getLifeCount(bean);
		map.put("finishtotal", String.valueOf(finishtotal));
		bean.setHelpApprove("1");//滿意
		int satis = infoReportBO.getLifeCount(bean);
		map.put("satis", String.valueOf(satis));
		bean.setHelpApprove("2");//基本滿意
		int basesatis = infoReportBO.getLifeCount(bean);
		map.put("basesatis", String.valueOf(basesatis));
		bean.setHelpApprove("4");//自行解決
		int self = infoReportBO.getLifeCount(bean);
		map.put("self", String.valueOf(self));
		
		if(finishtotal == 0) {
			map.put("percent", "0");
		} else {
			map.put("percent", calculatePercent(satis + basesatis + self, finishtotal));
		}
//		JSONObject jsonObject = JSONObject.fromObject(map);
		return map;
	}
	
	private String calculatePercent(int y, int z) {
		BigDecimal bigY = new BigDecimal(y);
		BigDecimal bigZ = new BigDecimal(z);
		
		BigDecimal percent = bigY.divide(bigZ, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		
		DecimalFormat format = new DecimalFormat("##.00");
		return format.format(percent);
	}
	
	
	@RequestMapping(params = "action=affairinforeport")
	public String affairInfoReport(
			@ModelAttribute("affairInfoSearchBean") AffairInfoSearchBean bean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		List<InformationVO> list = infoReportBO.findAffairInfoByParams(bean,
				pageInfo);
		Map<String, UserVO> userMap = userBO.findAll();
		Map<String, String> dictMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
		List<String> infoIds = new ArrayList<String>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			infoIds.add(infoVO.getInfoId());
		}
		
		
		Map<String, AffairInformationVO> affairInfoMap = infoReportBO.findAffairInfoByInfoIds(infoIds);
		List<AffairInfoReportDTO> dtoList = new ArrayList<AffairInfoReportDTO>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			AffairInfoReportDTO dto = new AffairInfoReportDTO();
			dto.setInfoId(infoVO.getInfoId());
			final AffairInformationVO	affairInfoVO = affairInfoMap.get(infoVO.getInfoId());
			if(null != affairInfoVO) {
				dto.setCallResult(null == affairInfoVO.getHelpApprove() ? "" : dictMap.get(affairInfoVO.getHelpApprove()));
				dto.setMoveWay(affairInfoVO.getMoveWay());
				dto.setMoveAcceptor(affairInfoVO.getMoveAcceptor());
			}
			
			dto.setCreateTime(infoVO.getCreateTime());
			dto.setCreator(userMap.get(infoVO.getCreator()).getUserName());
			dto.setHelpContent(infoVO.getHelpContent());
			dto.setHelpName(infoVO.getHelpName());
			dto.setHelpTel(infoVO.getHelpTel());
			dto.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
			dtoList.add(dto);
		}
		
		model.addAttribute("dtoList", dtoList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("affairInfoSearchBean", bean);
		
//		model.addAttribute("receiverTypeMap", Constants.LIFEINFOMATION_RECEIVETYPE_HASHMAP);
//		model.addAttribute("helpAreaList", dictBO.findByType(Constants.DICT_DICTTYPE_QZQY));
//		model.addAttribute("slrqList", dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ));
//		model.addAttribute("users", userMap.values());
		return "inforeport/affairinfolist";
	}
	
	
	@RequestMapping(params = "action=affairinfocount", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> getAffairInfoCount(@RequestParam String startDt, String endDt, String helpArea)
			throws UnsupportedEncodingException {
		AffairInfoSearchBean bean = new AffairInfoSearchBean();
		bean.setStartDt(startDt);
		bean.setEndDt(endDt);
		
		Map<String, String> map = new HashMap<String, String>();
		int total = infoReportBO.getAffairCount(bean);
		map.put("total", String.valueOf(total));
		bean.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		int finishtotal = infoReportBO.getAffairCount(bean);
		map.put("finishtotal", String.valueOf(finishtotal));
		bean.setHelpApprove("1");//滿意
		int satis = infoReportBO.getAffairCount(bean);
		map.put("satis", String.valueOf(satis));
		bean.setHelpApprove("2");//基本滿意
		int basesatis = infoReportBO.getAffairCount(bean);
		map.put("basesatis", String.valueOf(basesatis));
		bean.setHelpApprove("4");//自行解決
		int self = infoReportBO.getAffairCount(bean);
		map.put("self", String.valueOf(self));
		
		if(finishtotal == 0) {
			map.put("percent", "0");
		} else {
			map.put("percent", calculatePercent(satis + basesatis + self, finishtotal));
		}
//		JSONObject jsonObject = JSONObject.fromObject(map);
		return map;
	}
	
	@RequestMapping(params = "action=usertrafficreport")
	public  String  userTrafficReport(@ModelAttribute("trafficSearchBean") TrafficSearchBean bean, ModelMap model) {
		Map<String, UserTrafficDTO> map = new HashMap<String, UserTrafficDTO>();
		//life
		bean.setHelpType(Constants.INFOMATION_HELPTYPE_LIFE);
		List<UserTrafficBean> lifeList = userBO.findUserTraffic(bean);
		for (Iterator<UserTrafficBean> iter = lifeList.iterator(); iter.hasNext();) {
			UserTrafficBean userTrafficBean = iter.next();
			if(map.containsKey(userTrafficBean.getUserId())) {
				UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
				dto.setLifeTraffic(userTrafficBean.getTraffic());
			} else {
				UserTrafficDTO dto = new UserTrafficDTO();
				dto.setUserId(userTrafficBean.getUserId());
				dto.setLoginName(userTrafficBean.getLoginName());
				dto.setUserName(userTrafficBean.getUserName());
				dto.setLifeTraffic(userTrafficBean.getTraffic());
				map.put(dto.getUserId(), dto);
			}
		}
		
		//affair
		bean.setHelpType(Constants.INFOMATION_HELPTYPE_AFFAIR);
		List<UserTrafficBean> affairList = userBO.findUserTraffic(bean);
		for (Iterator<UserTrafficBean> iter = affairList.iterator(); iter.hasNext();) {
			UserTrafficBean userTrafficBean = iter.next();
			if(map.containsKey(userTrafficBean.getUserId())) {
				UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
				dto.setAffairTraffic(userTrafficBean.getTraffic());
			} else {
				UserTrafficDTO dto = new UserTrafficDTO();
				dto.setUserId(userTrafficBean.getUserId());
				dto.setLoginName(userTrafficBean.getLoginName());
				dto.setUserName(userTrafficBean.getUserName());
				dto.setAffairTraffic(userTrafficBean.getTraffic());
				map.put(dto.getUserId(), dto);
			}
		}
		
		
		//refer
		bean.setHelpType(Constants.INFOMATION_HELPTYPE_REFER);
		List<UserTrafficBean> referList = userBO.findUserTraffic(bean);
		for (Iterator<UserTrafficBean> iter = referList.iterator(); iter.hasNext();) {
			UserTrafficBean userTrafficBean = iter.next();
			if(map.containsKey(userTrafficBean.getUserId())) {
				UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
				dto.setReferTraffic(userTrafficBean.getTraffic());
			} else {
				UserTrafficDTO dto = new UserTrafficDTO();
				dto.setUserId(userTrafficBean.getUserId());
				dto.setLoginName(userTrafficBean.getLoginName());
				dto.setUserName(userTrafficBean.getUserName());
				dto.setReferTraffic(userTrafficBean.getTraffic());
				map.put(dto.getUserId(), dto);
			}
		}
		
		//fertility
		bean.setHelpType(Constants.INFOMATION_HELPTYPE_FERTILITY);
		List<UserTrafficBean> sclList = userBO.findUserTraffic(bean);
		for (Iterator<UserTrafficBean> iter = sclList.iterator(); iter.hasNext();) {
			UserTrafficBean userTrafficBean = iter.next();
			if(map.containsKey(userTrafficBean.getUserId())) {
				UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
				dto.setSclTraffic(userTrafficBean.getTraffic());
			} else {
				UserTrafficDTO dto = new UserTrafficDTO();
				dto.setUserId(userTrafficBean.getUserId());
				dto.setLoginName(userTrafficBean.getLoginName());
				dto.setUserName(userTrafficBean.getUserName());
				dto.setSclTraffic(userTrafficBean.getTraffic());
				map.put(dto.getUserId(), dto);
			}
		}
		
		//power
				bean.setHelpType(Constants.INFOMATION_HELPTYPE_POWER);
				List<UserTrafficBean> powerList = userBO.findUserTraffic(bean);
				for (Iterator<UserTrafficBean> iter = powerList.iterator(); iter.hasNext();) {
					UserTrafficBean userTrafficBean = iter.next();
					if(map.containsKey(userTrafficBean.getUserId())) {
						UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
						dto.setPowerTraffic(userTrafficBean.getTraffic());
					} else {
						UserTrafficDTO dto = new UserTrafficDTO();
						dto.setUserId(userTrafficBean.getUserId());
						dto.setLoginName(userTrafficBean.getLoginName());
						dto.setUserName(userTrafficBean.getUserName());
						dto.setPowerTraffic(userTrafficBean.getTraffic());
						map.put(dto.getUserId(), dto);
					}
				}
		
		//jdjt:lonelyhelp
		bean.setHelpType("LONELYHELP");
		List<UserTrafficBean> lhList = userBO.findUserJdjtTraffic(bean);
		for (Iterator<UserTrafficBean> iter = lhList.iterator(); iter.hasNext();) {
			UserTrafficBean userTrafficBean = iter.next();
			if(map.containsKey(userTrafficBean.getUserId())) {
				UserTrafficDTO dto = map.get(userTrafficBean.getUserId());
				dto.setJdjtTraffic(userTrafficBean.getTraffic());
			} else {
				UserTrafficDTO dto = new UserTrafficDTO();
				dto.setUserId(userTrafficBean.getUserId());
				dto.setLoginName(userTrafficBean.getLoginName());
				dto.setUserName(userTrafficBean.getUserName());
				dto.setJdjtTraffic(userTrafficBean.getTraffic());
				map.put(dto.getUserId(), dto);
			}
		}
		
		model.addAttribute("trafficSearchBean", bean);
		
		model.addAttribute("dtoList", map.values());
		return "inforeport/usertraffic";
	}
	
	@RequestMapping(params = "action=phonecount")
	public String TelCountReport(@ModelAttribute("helpCountByPhoneSearchBean") HelpCountByPhoneSearchBean bean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		final Date startDate = StringUtil.isNull(bean.getStartDt()) ? null : DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd");
		final Date endDate = StringUtil.isNull(bean.getEndDt()) ? null : DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd");
		List<HelpCountByPhoneBean> list = infoReportBO.getHelpCountByPhone(startDate, endDate, pageInfo);
		model.addAttribute("countList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "inforeport/helpcountbyphone";
	}
	
	@RequestMapping(params = "action=phonecountexp")
	public String TelCountReportToExcel(@ModelAttribute("helpCountByPhoneSearchBean") HelpCountByPhoneSearchBean bean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		
		pageInfo.setRows(1000);
		final Date startDate = StringUtil.isNull(bean.getStartDt()) ? null : DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd");
		final Date endDate = StringUtil.isNull(bean.getEndDt()) ? null : DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd");
		List<HelpCountByPhoneBean> list = infoReportBO.getHelpCountByPhone(startDate, endDate, pageInfo);
		model.addAttribute("countList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "inforeport/helpcountbyphone_excel";
	}
	
	
	
	@RequestMapping(params = "action=infoexport")
	public void infoExport(
			@ModelAttribute("infoSearchBean") InfoSearchBean infoSearchBean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("state", null);

		String path = request.getSession().getServletContext().getRealPath("template");

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		POIFSFileSystem fs = null;
		OutputStream fOut = null;

		// List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId,
		// areaSubId, startDt, endDt);
		//
		// JSONObject jsonObj = new JSONObject();
		// jsonObj.put("total", beanList.size());
		//
		// JSONArray jsonArray = JSONArray.fromObject(beanList);
		//
		// jsonObj.put("rows", jsonArray.toString());
		HSSFWorkbook workbook = null;
		final String userAgent = request.getHeader("USER-AGENT");
		try {

			if ("\\".equals(File.separator)) { // windows
				fs = new POIFSFileSystem(new FileInputStream(path + "\\info_exp_list.xls"));
			} else { // linux
				fs = new POIFSFileSystem(new FileInputStream(path + "/info_exp_list.xls"));
			}
			// 进行转码，使其支持中文文件名
			codedFileName = "求助受理记录表";

			String finalFileName = null;
			if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
				finalFileName = URLEncoder.encode(codedFileName, "UTF8");
			} else if (StringUtils.contains(StringUtils.lowerCase(userAgent), "firefox")) {// google,火狐浏览器
				finalFileName = new String(codedFileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(codedFileName, "UTF8");// 其他浏览器
			}
			response.setHeader("content-disposition", "attachment;filename=" + finalFileName + ".xls");
			response.setContentType("application/vnd.ms-excel");
			// response.addHeader("Content-Disposition", "attachment; filename="
			// + codedFileName + ".xls");
			// 产生工作簿对象
			workbook = new HSSFWorkbook(fs);
			// 产生工作表对象
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow titleRow = sheet.getRow(0);
			titleRow.getCell(0).setCellValue(
					titleRow.getCell(0).getStringCellValue() + "(" + DateUtil.format(new Date(), "yyyy-MM-dd") + ")");
			
			titleRow = sheet.getRow(1);
			titleRow.getCell(0).setCellValue(infoSearchBean.getStartDt() + " ---- " + infoSearchBean.getEndDt());
			
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(1);
			pageInfo.setRows(999999999);
			
			List<InformationVO> list = infoReportBO.findByParams(infoSearchBean,
					pageInfo);
			
//			System.out.println("Tims spend: " + String.valueOf(System.currentTimeMillis() - startTime));
			
			Map<String, UserVO> userMap = userBO.findAll();
			Map<String, String> dictMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
//			Map<String, String> resultMap = infoReportBO.getResult(list);
			
			List<InfoReportDTO> dtoList = new ArrayList<InfoReportDTO>();
			for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
				InformationVO infoVO = iter.next();
				InfoReportDTO dto = new InfoReportDTO();
				dto.setInfoId(infoVO.getInfoId());
				String result = "";
				/*if (Constants.INFOMATION_HELPTYPE_LIFE.equals(infoVO.getHelpType())) {
					LifeInformationVO lifeInfoVO = infoReportBO.findLifeInfoByInfoId(infoVO.getInfoId());
					if(null != lifeInfoVO) {
						result = lifeInfoVO.getHelpApprove();
					}
				} else if (Constants.INFOMATION_HELPTYPE_AFFAIR.equals(infoVO.getHelpType())) {
					AffairInformationVO affairInfoVO = infoReportBO.findAffairInfoByInfoId(infoVO.getInfoId());
					if(null != affairInfoVO) {
						result = affairInfoVO.getHelpApprove();
					}
				}*/
				
				dto.setCallResult(StringUtils.isEmpty(result) ? "" : dictMap.get(result));
				dto.setCreateTime(infoVO.getCreateTime());
				dto.setCreator(userMap.get(infoVO.getCreator()).getUserName());
				dto.setHelpContent(infoVO.getHelpContent());
				dto.setHelpName(infoVO.getHelpName());
				dto.setHelpTel(infoVO.getHelpTel());
				dto.setHelpType(infoVO.getHelpType());
				dto.setHelpTypeValue(Constants.INFOMATION_HELPTYPE_HASHMAP.get(infoVO.getHelpType()));
				dto.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
				dtoList.add(dto);
			}
			for (int i = 0; i < dtoList.size(); i++) {
				InfoReportDTO dto = dtoList.get(i);
				HSSFRow row = sheet.createRow(i + 3);
				row.createCell(0).setCellValue(String.valueOf(i + 1));
				row.createCell(1).setCellValue(dto.getHelpName());
				row.createCell(2).setCellValue(dto.getCreateTime());
				row.createCell(3).setCellValue(dto.getHelpContent());
				row.createCell(4).setCellValue(dto.getHelpTel());
				row.createCell(5).setCellValue(dto.getHelpTypeValue());
				row.createCell(6).setCellValue(dto.getCreator());
				if(StringUtil.isNotEmpty(dto.getCallResult())) {
					row.createCell(7).setCellValue(dto.getCallResult());
				} else {
					row.createCell(7).setCellValue("-");
				}
				row.createCell(8).setCellValue(dto.getStatus());
			}

			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				fOut.flush();
				fOut.close();
				fs.close();
			} catch (IOException e) {
			}
			session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}
}

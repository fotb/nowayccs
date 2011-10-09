package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bean.InfoReportDTO;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoReportDTO;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IInfoReportBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;

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
	
	@RequestMapping
	public String list(
			@ModelAttribute("infoSearchBean") InfoSearchBean infoSearchBean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		List<InformationVO> list = infoReportBO.findByParams(infoSearchBean,
				pageInfo);
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
		model.addAttribute("users", userMap.values());
		return "inforeport/infolist";
	}
	
	@RequestMapping(params = "action=infocount", method = RequestMethod.GET)
	public @ResponseBody
	String getInfoCount(@RequestParam String creator, String helpType,
			String helpArea, String helpGroup, String startDt, String endDt)
			throws UnsupportedEncodingException {
		InfoSearchBean infoSearchBean = new InfoSearchBean();
		infoSearchBean.setCreator(creator);
		infoSearchBean.setHelpType(helpType);
		infoSearchBean.setHelpArea(helpArea);
		infoSearchBean.setHelpGroup(helpGroup);
		infoSearchBean.setStartDt(startDt);
		infoSearchBean.setEndDt(endDt);
		
		Map<String, String> map = new HashMap<String, String>();
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
			
			map.put("count", String.valueOf(lifeCount + affairCount + referCount + fertilityCount));
		} else {
			int count = infoReportBO.getCountByParams(infoSearchBean);
			map.put("count", String.valueOf(count));
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
			} else {
				map.put("fertilityCount", "0");
			}
		}
		
		int life = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_LIFE);
		int affair = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_AFFAIR);
		int refer = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_REFER);
		int fertility = infoReportBO.getInfoCount(Constants.INFOMATION_HELPTYPE_FERTILITY);

		map.put("life", String.valueOf(life));
		map.put("affair", String.valueOf(affair));
		map.put("refer", String.valueOf(refer));
		map.put("fertility", String.valueOf(fertility));
		map.put("total", String.valueOf(life + affair + refer + fertility));
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
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
			if(Constants.LIFEINFOMATION_RECEIVETYPE_QY.equals(lifeInfoVO.getReceiverType())) {
				EntpriseVO entVO = entMap.get(lifeInfoVO.getReceiverId());
				dto.setReceiver(entVO.getEntpriseName());
			} else {
				VolunteerVO vltVO = vltMap.get(lifeInfoVO.getReceiverId());
				dto.setReceiver(vltVO.getVolunteerName());
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
		model.addAttribute("users", userMap.values());
		return "inforeport/lifeinfolist";
	}
	
	@RequestMapping(params = "action=lifeinfocount", method = RequestMethod.GET)
	public @ResponseBody
	String getLifeInfoCount(@RequestParam String receiverType, String keyWords, 
			String startDt, String endDt, String helpArea)
			throws UnsupportedEncodingException {
		LifeInfoSearchBean bean = new LifeInfoSearchBean();
		bean.setReceiverType(receiverType);
		bean.setKeyWords(keyWords);
		bean.setStartDt(startDt);
		bean.setEndDt(endDt);
		bean.setHelpArea(helpArea);
		
		Map<String, String> map = new HashMap<String, String>();
		int total = infoReportBO.getLifeCount(bean);
		map.put("total", String.valueOf(total));
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
		
		
		map.put("percent", calculatePercent(satis + basesatis + self, finishtotal));
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	private String calculatePercent(int y, int z) {
		BigDecimal bigY = new BigDecimal(y);
		BigDecimal bigZ = new BigDecimal(z);
		
		BigDecimal percent = bigY.divide(bigZ, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		
		DecimalFormat format = new DecimalFormat("##.00");
		return format.format(percent);
	}
}

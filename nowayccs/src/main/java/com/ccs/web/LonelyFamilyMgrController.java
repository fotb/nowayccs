package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IDictBO;
import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.web.domain.LfMgrForm;

@Controller
@RequestMapping("/lfmgr.do")
public class LonelyFamilyMgrController {
	private static final Logger logger = Logger.getLogger(LonelyFamilyMgrController.class);
	
	@Autowired
	private ILonelyFamilyBO lonelyFamilyBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
	public String list(
			@ModelAttribute("lfMgrForm") LfMgrForm lfMgrForm,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}

		if (null == lfMgrForm) {
			lfMgrForm = new LfMgrForm();
		}

		List<LonelyManInfoVO> voList = lonelyFamilyBO.queryLonelyManInfo(lfMgrForm, pageInfo);
		
		model.addAttribute("sftMap", Constants.SPECIAL_FAMILY_MAP);

		model.addAttribute("lmiVOList", voList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("lfMgrForm", lfMgrForm);

		return "specialfamily/lonelymanlist";
	}
	
	@RequestMapping(params = "action=add")
	public String add(@RequestParam String pageNo, ModelMap model) {
		LonelyManInfoVO lmiVO = new LonelyManInfoVO();
		model.addAttribute("lmiVO", lmiVO);
		model.addAttribute("pageNo", pageNo);
		return "specialfamily/lonelymanadd";
	}
	
	@RequestMapping(params = "action=saveorupdate")
	public String save(@ModelAttribute LonelyManInfoVO LonelyManInfoVO, @RequestParam String pageNo, ModelMap model) {
		lonelyFamilyBO.saveOrUpdate(LonelyManInfoVO);
		return "redirect:lfmgr.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=update")
	public String update(@RequestParam String manId, @RequestParam String pageNo, ModelMap model) {
		LonelyManInfoVO lmiVO = lonelyFamilyBO.findLonelyManInfoByManId(manId);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lmiVO", lmiVO);
		return "specialfamily/lonelymanedit";
	}
	
	
	
	@RequestMapping(params = "action=del")
	public String delete(@RequestParam String manId, @RequestParam String pageNo, ModelMap model) {
		lonelyFamilyBO.delLonelyFamily(manId);
		return "redirect:lfmgr.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=pmlist")
	public String pmList(@RequestParam String manId, HttpSession session, ModelMap model) {

		List<PartyMemberForLonelyVO> pmVOList = lonelyFamilyBO.findByManId(manId);

		model.addAttribute("pmVOList", pmVOList);
		model.addAttribute("manId", manId);
		return "specialfamily/partymemberlist";
	}
	
	@RequestMapping(params = "action=pmadd")
	public String pmAdd(@RequestParam String manId, ModelMap model) {
		PartyMemberForLonelyVO pmVO = new PartyMemberForLonelyVO();
		model.addAttribute("pmVO", pmVO);
		model.addAttribute("manId", manId);
		return "specialfamily/partymemberadd";
	}
	
	@RequestMapping(params = "action=pmsaveorupdate")
	public String pmSave(@ModelAttribute PartyMemberForLonelyVO pmVO, @RequestParam String manId, ModelMap model) {
		pmVO.setLonelyManId(manId);
		lonelyFamilyBO.saveOrUpdate(pmVO);
//		model.addAttribute("manId", manId);
		return "redirect:lfmgr.do?action=pmlist&manId=" + manId;
	}
	
	@RequestMapping(params = "action=pmupdate")
	public String pmUpdate(@RequestParam String manId,@RequestParam String memberId, ModelMap model) {
		PartyMemberForLonelyVO pmVO = lonelyFamilyBO.findPartyMemberForLonelyById(memberId);
		model.addAttribute("manId", manId);
		model.addAttribute("pmVO", pmVO);
		return "specialfamily/partymemberedit";
	}
	
	@RequestMapping(params = "action=pmdel")
	public String pmDel(@RequestParam String memberId, @RequestParam String manId, ModelMap model) {
		lonelyFamilyBO.pmDel(memberId);
//		model.addAttribute("manId", manId);
		return "redirect:lfmgr.do?action=pmlist&manId=" + manId;
	}
}

package com.ccs.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IDictBO;
import com.ccs.bo.IReferBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.DictVO;
import com.ccs.vo.ReferVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/refer.do")
public class referController {

	@Autowired
	private IReferBO referBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@RequestMapping
	public String list(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "referType", required = false) String referType,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.parseInt(pageNo));
		List<ReferVO> referList = referBO.findByParams(StringUtil.emptyToNull(title), StringUtil.emptyToNull(referType), pageInfo);
		model.addAttribute("referList", referList);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(userBO.hasOperationRight(user.getUserId(), Constants.SYS_PERMISSION_YWZXWH)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}

		List<DictVO> dictList = dictBO.findByType(Constants.DICT_DICTTYPE_ZXLX);
		model.addAttribute("zxlxDictList", dictList);
		Map<String, String> zxlxDictMap = dictBO.getDict(Constants.DICT_DICTTYPE_ZXLX);
		model.addAttribute("zxlxDictMap", zxlxDictMap);
		
		model.addAttribute("title", title);
		model.addAttribute("referType", referType);
		model.addAttribute("pageInfo", pageInfo);
		return "refer/list";
	}
	
	@RequestMapping(params = "action=add")
	public String add(ModelMap model) {
		List<DictVO> dictList = dictBO.findByType(Constants.DICT_DICTTYPE_ZXLX);
		model.addAttribute("zxlxDictList", dictList);
		
		model.addAttribute("referVO", new ReferVO());
		
		return "refer/add";
	}
	
	@RequestMapping(params = "action=addsave")
	public String addSave(@ModelAttribute("referVO") ReferVO referVO, ModelMap model) {
		referBO.saveoOrUpdate(referVO);
		return "redirect:refer.do";
	}
	
	@RequestMapping(params = "action=edit")
	public String edit(String referId, String pageNo, ModelMap model) {
		List<DictVO> dictList = dictBO.findByType(Constants.DICT_DICTTYPE_ZXLX);
		model.addAttribute("zxlxDictList", dictList);
		
		model.addAttribute("referVO", referBO.findById(referId));
		
		model.addAttribute("pageNo", pageNo);
		return "refer/edit";
	}
	
	@RequestMapping(params = "action=editsave")
	public String editSave(@ModelAttribute("referVO") ReferVO referVO, String pageNo, ModelMap model) {
		referBO.saveoOrUpdate(referVO);
		return "redirect:refer.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=view")
	public String view(String referId, ModelMap model) {
		model.addAttribute("referVO", referBO.findById(referId));
		Map<String, String> zxlxDictMap = dictBO.getDict(Constants.DICT_DICTTYPE_ZXLX);
		model.addAttribute("zxlxDictMap", zxlxDictMap);
		
		return "refer/view";
	}
	
	@RequestMapping(params = "action=del")
	public String delete(String referId, String pageNo, ModelMap model) {
		ReferVO referVO = referBO.findById(referId);
		referBO.delete(referVO);
		
		return "redirect:refer.do?pageNo=" + pageNo;
	}
}

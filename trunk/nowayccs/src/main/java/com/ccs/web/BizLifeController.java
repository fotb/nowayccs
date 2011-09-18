package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.IDictBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LifeDispatchBean;

@Controller
@RequestMapping("/bizlife.do")
public class BizLifeController {

	@Autowired
	private IBizLifeBO bizLifeBO;
	
	@Autowired
	private IDictBO dictBO;

	@RequestMapping
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {

		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		UserVO userVO = (UserVO) session.getAttribute("currentUser");

		List<InformationVO> list = bizLifeBO.findByCreatorAndStatus(
				userVO.getUserId(), Constants.SYS_INFOMATION_STATES_DB,
				Constants.INFOMATION_HELPTYPE_LIFE, pageInfo);

		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		model.addAttribute("infoList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "life/list";
	}

	@RequestMapping(params = "action=dispatch")
	public String dispatch(@RequestParam("infoId") String infoId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		InformationVO infoVO = bizLifeBO.findInfoByInfoId(infoId);

		model.addAttribute("infoVO", infoVO);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lifeDispatchBean", new LifeDispatchBean());
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsList", dictBO.findByType(Constants.DICT_DICTTYPE_LLFS));
		return "life/dispatch";
	}
}

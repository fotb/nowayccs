package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IBizAffairBO;
import com.ccs.bo.IDictBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.AffairDispatchBean;

@Controller
@RequestMapping("/bizaffair.do")
public class BizAffairController {
	
	@Autowired
	private IBizAffairBO bizAffairBO;
	
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
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);

		List<InformationVO> list = bizAffairBO.findInfoByParams(
				userVO.getUserId(), Constants.SYS_INFOMATION_STATES_DB,
				Constants.INFOMATION_HELPTYPE_AFFAIR, pageInfo);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		model.addAttribute("infoList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "affair/list";
	}
	
	@RequestMapping(params = "action=dispatch")
	public String dispatch(@RequestParam("infoId") String infoId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		InformationVO infoVO = bizAffairBO.findInfoByInfoId(infoId);

		model.addAttribute("infoVO", infoVO);
		model.addAttribute("pageNo", pageNo);
		AffairDispatchBean bean = new AffairDispatchBean();
		bean.setInfoId(infoVO.getInfoId());
		model.addAttribute("affairDispatchBean", bean);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsList", dictBO.findByType(Constants.DICT_DICTTYPE_LLFS));
		return "affair/dispatch";
	}
	
	@RequestMapping(params = "action=dispatchsave")
	public String dispatchSave(@ModelAttribute("affairDispatchBean") AffairDispatchBean affairDispatchBean,
			@RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {
		AffairInformationVO vo = bizAffairBO.findAffairInfoByInfoId(affairDispatchBean.getInfoId());
		vo.setMoveWay(affairDispatchBean.getMoveWay());
		vo.setMoveAcceptor(affairDispatchBean.getMoveAcceptor());
		vo.setMoveAcceptTel(affairDispatchBean.getAcceptorTel());
		vo.setMoveMode(affairDispatchBean.getMoveMode());
		vo.setMoveTime(DateUtil.parse(affairDispatchBean.getMoveTime(), "yyyy-MM-dd HH:mm"));
		
		bizAffairBO.addAffairInfo(vo);
		return "redirect:bizaffair.do?pageNo=" + pageNo;
	}
}

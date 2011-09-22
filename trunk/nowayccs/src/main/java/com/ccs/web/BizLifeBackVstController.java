package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IBizLifeBackVstBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/bizlifebackvst.do")
public class BizLifeBackVstController {

	@Autowired
	private IBizLifeBackVstBO bizLifeBackVstBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
	public String list(@RequestParam(value = "deliverer", required = false) String deliverer,
			@RequestParam(value = "pageNo", required = false) String pageNo, HttpSession session,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.parseInt(pageNo));
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		String userId = null == deliverer ? user.getUserId() : deliverer;
		List<InformationVO> list = bizLifeBackVstBO.findByDeliverer(userId, Constants.INFOMATION_HELPTYPE_LIFE, pageInfo);
		
		List<UserVO> userList = userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SHYWCL);
		model.addAttribute("infoList", list);
		model.addAttribute("userList", userList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("deliverer", userId);
		return "life/backvst/list";
	}
}

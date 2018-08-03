package com.ccs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.bo.IUserAppInfoBO;
import com.ccs.util.Constants;
import com.ccs.vo.AppReceiverVO;
import com.ccs.vo.UserAppInfoVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/userAppInfo.do")
public class UserAppInfoController {

	@Autowired
	private IAppReceiverBO appReceiverBO;

	@Autowired
	private IUserAppInfoBO userAppInfoBO;

	@RequestMapping
	public String list(HttpSession session, ModelMap model) {
		return "app/list";
	}

	@RequestMapping(params = "action=get")
	@ResponseBody
	public List<AppReceiverVO> getInfo(HttpSession session) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);

		List<UserAppInfoVO> userAppInfoList = userAppInfoBO.findByUserId(user.getUserId());
		List<String> ids = new ArrayList<String>();
		for (UserAppInfoVO vo : userAppInfoList) {
			ids.add(vo.getAppInfoId());
		}

		List<AppReceiverVO> appReceiverVOList = appReceiverBO.findByIds(ids);
//		JSONArray jsonObj = JSONArray.fromObject(appReceiverVOList);
		return appReceiverVOList;
	}

}

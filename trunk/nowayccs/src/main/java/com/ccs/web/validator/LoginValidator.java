package com.ccs.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ccs.bo.IUserBO;
import com.ccs.util.StringUtil;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LoginBean;
import com.ccs.web.domain.User;

@Component("loginValidator")
public class LoginValidator implements Validator {

	@Autowired
	private IUserBO userBO;
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginBean loginBean = (LoginBean) target;
		if(StringUtil.isNull(loginBean.getLoginName())) {
			errors.rejectValue("loginName", "notExistUser", null, "请输入用户名！");
			return;
		}
		if(StringUtil.isNull(loginBean.getLoginPassword())) {
			errors.rejectValue("loginPassword", "errorPassword", null, "请输入密码！");
			return;
		}
		final UserVO userVO = userBO.login(loginBean.getLoginName(), loginBean.getLoginPassword());
		if(null == userVO) {
			if(!isUserExist(loginBean.getLoginName())) {
				errors.rejectValue("loginName", "notExistUser", null, "用户不存在！");
			} else {
				errors.rejectValue("loginPassword", "errorPassword", null, "密码错误！");
			}
		}
	}
	
	private boolean isUserExist(String loginName) {
		List<UserVO> list = userBO.findByLoginName(loginName);
		return list.isEmpty() ? false : true;
	}
}

package com.ccs.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ccs.bo.IUserBO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.User;

@Component("userValidator")
public class UserValidator implements Validator {

	@Autowired
	private IUserBO userBO;
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(isUserExist(user.getLoginName())) {
			errors.rejectValue("loginName", "userNameExist", null, "登录名已存在，请重新输入！");
		}
		if(!isSamePwd(user.getLoginPassword(), user.getDoublePassword())) {
			errors.rejectValue("loginPassword", "doublepwderror", null, "两次输入的密码不一致，请重新输入！");
		}
	}
	
	private boolean isUserExist(String loginName) {
		List<UserVO> list = userBO.findByLoginName(loginName);
		return list.isEmpty() ? false : true;
	}
	
	private boolean isSamePwd(String pwd1, String pwd2) {
		return pwd1.equals(pwd2) ? true : false;
	}
}

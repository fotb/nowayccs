package com.ccs.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ccs.bo.IBlackListBO;
import com.ccs.vo.BlackListVO;
import com.ccs.web.domain.User;

@Component("blackListValidator")
public class BlackListValidator implements Validator {

	@Autowired
	private IBlackListBO blackListBO;
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BlackListVO vo = (BlackListVO) target;
		if(isPhoneExist(vo.getPhoneNum())) {
			errors.rejectValue("phoneNum", "PhoneExist", null, "电话号码已存在，请重新输入！");
		}
		
	}
	
	private boolean isPhoneExist(String phoneNum) {
		BlackListVO vo = blackListBO.findByPhoneNum(null == phoneNum ? "" : phoneNum.trim());
		return null == vo ? false : true;
	}
	
}

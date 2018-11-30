package com.ccs.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.vo.OperationVO;
import com.ccs.vo.UserVO;

public class RightInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private IUserBO userBO;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String className = handler.getClass().getName();

//		System.out.println("class name: " + className);

		String action = request.getParameter("action");
//		System.out.println("action : " + action);

		System.out.println("action : " + className + action);
		
		if ("com.ccs.web.LoginController".equals(className) || "com.ccs.web.IndexController".equals(className)
				|| "com.ccs.web.AppReceiverController".equals(className)
				|| "com.ccs.web.SgptReceiveController".equals(className)) {
			return true;
		} else {
			UserVO loginUser = (UserVO) request.getSession().getAttribute(	Constants.SESSION_USER_KEY);
			if (null != loginUser) {
				Map<String, OperationVO> map = userBO.findUserOpertaionRightByUserId(loginUser.getUserId());
				if(map.containsKey(className)) {
					return true;
				} else {
					request.getRequestDispatcher("/index.do?action=noright").forward(request, response); 
					return false;
				}
			} else {
				return true;
			}
		}
	}

}

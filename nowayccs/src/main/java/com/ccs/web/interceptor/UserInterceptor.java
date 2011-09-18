package com.ccs.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ccs.vo.UserVO;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String className = handler.getClass().getName();
		if("com.ccs.web.LoginController".equals(className)) {
			return true;
		} else {
			UserVO loginUser = (UserVO)request.getSession().getAttribute("currentUser"); 
			if(null == loginUser) {
				request.getRequestDispatcher("/login.do").forward(request, response); 
			}
			return null == loginUser ? false : true;
		}
	}

}

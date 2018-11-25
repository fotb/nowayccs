package com.ccs.web.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ccs.bo.IUserBO;
import com.ccs.bo.IUserStatusBO;
import com.ccs.util.Constants;
import com.ccs.vo.OperationVO;
import com.ccs.vo.UserVO;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IUserStatusBO userStatusBO;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
<<<<<<< HEAD
		String className = handler.getClass().getName();
=======
//		String className = handler.getClass().getName();
		HandlerMethod method = (HandlerMethod) handler;
		String className = method.getBean().getClass().getName();
>>>>>>> refs/remotes/origin/NewDev
		if("com.ccs.web.LoginController".equals(className)) {
			return true;
		} else {
			UserVO loginUser = (UserVO)request.getSession().getAttribute(Constants.SESSION_USER_KEY); 
			if(null == loginUser) {
				userStatusBO.updateTimeoutUserStatus(new Date());
				request.getRequestDispatcher("/login.do?action=relogin").forward(request, response); 
				return false;
			} else if(!"com.ccs.web.IndexController".equals(className) && !"com.ccs.web.CommonJsonController".equals(className)){
				String action = request.getParameter("action");
//				System.out.println("className + action : " + className + "-" + action);
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

package com.ccs.dao;

import java.util.List;

import com.ccs.bean.TrafficSearchBean;
import com.ccs.bean.UserTrafficBean;
import com.ccs.util.PageInfo;
import com.ccs.vo.UserVO;

public interface IUserDAO {
	void saveOrUpdate(UserVO vo);
	
	void update(UserVO vo);
	
	void delete(UserVO vo);
	
	UserVO findById(String userId);
	
	List<UserVO> findAll(PageInfo pageInfo);
	
	List<UserVO> findAll();
	
	UserVO findByLoginNameAndPwd(String loginName, String pwd);
	
	int getTotalCount();
	
	List<UserVO> findByLoginName(String loginName);
	
	List<UserVO> findAllOnJob();
	
	List<UserVO> findByUserIds(List<String> userIdList);
	
	List<UserTrafficBean> findUserTraffic(TrafficSearchBean bean);
	
	List<UserTrafficBean> findUserJdjtTraffic(TrafficSearchBean bean);
}

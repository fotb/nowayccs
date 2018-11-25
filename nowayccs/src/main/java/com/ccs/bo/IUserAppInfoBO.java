package com.ccs.bo;

import java.util.List;

import com.ccs.vo.UserAppInfoVO;

public interface IUserAppInfoBO {
	
	public void create(List<UserAppInfoVO> list) throws Exception;
	
	public List<UserAppInfoVO> findByUserId(String userId) throws Exception;
	
	public List<UserAppInfoVO> findByAppInfoId(String appInfoId) throws Exception;
	
	public void delete(UserAppInfoVO vo) throws Exception;
	
	public void updateUserAppInfo() throws Exception;
}

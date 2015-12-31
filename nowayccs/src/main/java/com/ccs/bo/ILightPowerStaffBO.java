package com.ccs.bo;

import java.util.List;

import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;

public interface ILightPowerStaffBO {
	
	void saveLPS(PowerStaffDomain psDomain, UserVO userVO) throws Exception;
	
	LightPowerStaffTreeBean buildLPSTree() throws Exception;
	
	void deleteLPS(String id, UserVO userVO) throws Exception;
	
	PowerStaffVO findPowerStaffById(String id) throws Exception;
	
	void updatePowerStaff(PowerStaffVO psVO) throws Exception;
	
	List<PowerStaffAreaVO> findPSAById(String id) throws Exception;
	
}

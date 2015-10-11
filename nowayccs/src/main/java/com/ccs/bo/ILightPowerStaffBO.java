package com.ccs.bo;

import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;

public interface ILightPowerStaffBO {
	
	void saveLPS(PowerStaffDomain psDomain, UserVO userVO) throws Exception;
	
	LightPowerStaffTreeBean buildLPSTree() throws Exception;
	
	void deleteLPS(String id, UserVO userVO) throws Exception;
	
}

package com.ccs.bo;

import java.util.List;

import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffVO;

public interface IPowerInformationBO {
	void saveOrUpdate(PowerInformationVO piVO) throws Exception;
	
	List<PowerStaffVO> findByAreaSubId(final String areaSubId) throws Exception;
	
	PowerInformationVO findById(String id);
	
	List<PowerInformationVO> findByInfoId(String infoId);
}

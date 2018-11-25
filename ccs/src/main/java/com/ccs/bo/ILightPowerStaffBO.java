package com.ccs.bo;

import java.util.List;

import com.ccs.util.EasyUiTree;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerInfoListBean;
import com.ccs.web.domain.PowerStaffDomain;
import com.ccs.web.domain.PowerStaffListBean;
import com.ccs.web.domain.PowerStaffReportBean;

public interface ILightPowerStaffBO {

	void saveLPS(PowerStaffDomain psDomain, UserVO userVO) throws Exception;

	LightPowerStaffTreeBean buildLPSTree() throws Exception;
	
	List<PowerStaffListBean> buildList(String areaId, int page, int rows) throws Exception;
	
	int countBuildList(String areaId) throws Exception;

	void deleteLPS(String id, UserVO userVO) throws Exception;

	PowerStaffVO findPowerStaffById(String id) throws Exception;

	void updatePowerStaff(PowerStaffVO psVO) throws Exception;

	List<PowerStaffAreaVO> findPSAById(String id) throws Exception;

	List<PowerStaffReportBean> powerStaffReport(String areaId, String areaSubId, String startDt, String endDt)
			throws Exception;

	
	List<PowerStaffVO> findAll() throws Exception;
	
	List<PowerStaffVO> queryAllOrderByAreaSubId(String areaSubId, String psname, String psphone) throws Exception;
	
	int countPSByAreaSubId(String areaSubId) throws Exception;
	
	void saveOrUpdate(List<PowerStaffAreaVO> psaVOList) throws Exception;
	
	
	List<EasyUiTree> buildAreaTree() throws Exception;
	
	void associateSave(UserVO user, String areaSubId, String[] staffIds) throws Exception;
	
	List<PowerInfoListBean> queryPowerInfo(String startDt, String endDt, int page, int rows) throws Exception;
	
	int queryPowerInfoCount(String startDt, String endDt) throws Exception;
	
	List<PowerInfoListBean> queryPowerInfo(String startDt, String endDt) throws Exception;
	
	void fix() throws Exception;
}

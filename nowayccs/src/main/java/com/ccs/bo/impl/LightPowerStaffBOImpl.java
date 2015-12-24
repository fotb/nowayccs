package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.dao.IAreaDAO;
import com.ccs.dao.IAreaSubDAO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LPSRowBean;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;

@Service("lpsBO")
public class LightPowerStaffBOImpl implements ILightPowerStaffBO {

	@Autowired
	private IBaseDAO<PowerStaffVO> lpsDAO; 
	
	@Autowired
	private IBaseDAO<PowerStaffAreaVO> lpsaDAO; 
	
	@Autowired
	private IAreaDAO areaDAO;
	
	@Autowired
	private IAreaSubDAO areaSubDAO;
	
	@Override
	@Transactional
	public void saveLPS(PowerStaffDomain psDomain, UserVO userVO) throws Exception {
		PowerStaffVO psVO = new PowerStaffVO();
		psVO.setCreateTime(new Date());
		psVO.setName(psDomain.getName());
		psVO.setPhone(psDomain.getPhone());
		psVO.setRemark(psDomain.getRemark());
		psVO.setCategory(psDomain.getCategory());
		psVO.setLastHandler(userVO.getUserId());
		lpsDAO.save(psVO);
		
		
		
		if(PowerStaffVO.CATEGORY_4.equals(psDomain.getCategory())) {
			String[] areaId = psDomain.getAreaSubId().split(",");
			for (String id : areaId) {
				PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
				psaVO.setAreaSubId(id);
				psaVO.setCreateTime(new Date());
				psaVO.setStaffId(psVO.getPid());
				psaVO.setLastHandler(userVO.getUserId());
				lpsaDAO.save(psaVO);
			}
		} else {
			PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
			psaVO.setAreaSubId(psDomain.getAreaId());
			psaVO.setCreateTime(new Date());
			psaVO.setStaffId(psVO.getPid());
			psaVO.setLastHandler(userVO.getUserId());
			lpsaDAO.save(psaVO);
		}
	}

	@Override
	public LightPowerStaffTreeBean buildLPSTree() throws Exception {
		
		List<AreaVO> areaList = areaDAO.findAll();
		Map<String, AreaVO> areaMap = new HashMap<String, AreaVO>();
		for (AreaVO areaVO : areaList) {
			areaMap.put(areaVO.getAreaId(), areaVO);
		}
		
		List<AreaSubVO> areaSubList = areaSubDAO.findAll();
		Map<String, AreaSubVO> areaSubMap = new HashMap<String, AreaSubVO>();
		for (AreaSubVO areaSubVO : areaSubList) {
			areaSubMap.put(areaSubVO.getAreaSubId(), areaSubVO);
		}
		
		
		List<PowerStaffVO> list = lpsDAO.getAll();
		Map<String, PowerStaffVO> psVOMap = new HashMap<String, PowerStaffVO>();
		for (PowerStaffVO powerStaffVO : list) {
			psVOMap.put(powerStaffVO.getPid(), powerStaffVO);
		}
		
		Map<String, AreaVO> lpsAreaMap = new HashMap<String, AreaVO>();
		Map<String, AreaSubVO> lpsAreaSubMap = new HashMap<String, AreaSubVO>();
		
		List<PowerStaffAreaVO> psaList = lpsaDAO.getAll();
		LightPowerStaffTreeBean lpsTreeBean = new LightPowerStaffTreeBean();
		List<LPSRowBean> lpsRowBeanList = new ArrayList<LPSRowBean>();
		for (PowerStaffAreaVO psaVO : psaList) {
			PowerStaffVO psVO = psVOMap.get(psaVO.getStaffId());
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(psVO.getPid());
			lpsRowBean.setName(psVO.getName());
			lpsRowBean.setPhone(psVO.getPhone());
			lpsRowBean.setRemark(psVO.getRemark());
			lpsRowBean.setCategory(psVO.getCategory());
//			lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBean.set_parentId(psaVO.getAreaSubId());
			lpsRowBeanList.add(lpsRowBean);
			
			if(PowerStaffVO.CATEGORY_4.equals(psVO.getCategory())) {
				if(!lpsAreaSubMap.containsKey(psaVO.getAreaSubId())) {
				lpsAreaSubMap.put(psaVO.getAreaSubId(), areaSubMap.get(psaVO.getAreaSubId()));
				}
			
				String psAreaId = areaSubMap.get(psaVO.getAreaSubId()).getAreaId();
				if(!lpsAreaMap.containsKey(psAreaId)) {
					lpsAreaMap.put(psAreaId, areaMap.get(psAreaId));
				}
			} else {
				if(!lpsAreaMap.containsKey(psaVO.getAreaSubId())) {
					lpsAreaMap.put(psaVO.getAreaSubId(), areaMap.get(psaVO.getAreaSubId()));
				}
			}
			
		}
		
		for (AreaVO areaVO : lpsAreaMap.values()) {
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(areaVO.getAreaId());
			lpsRowBean.setName(areaVO.getName());
//			lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBeanList.add(lpsRowBean);
		}
		
		for (AreaSubVO areaSubVO : lpsAreaSubMap.values()) {
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(areaSubVO.getAreaSubId());
			lpsRowBean.setName(areaSubVO.getName());
//			lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBean.set_parentId(areaSubVO.getAreaId());
			lpsRowBeanList.add(lpsRowBean);
		}
		lpsTreeBean.setRows(lpsRowBeanList);
		lpsTreeBean.setTotal(String.valueOf(lpsRowBeanList.size()));
		return lpsTreeBean;
	}

	@Override
	public void deleteLPS(String id, UserVO userVO) throws Exception {
		PowerStaffVO psVO = lpsDAO.get(id);
		psVO.setDeleteFlag(PowerStaffVO.DELETE_FLAG_YES);
		psVO.setUpdateDT(new Date());
		psVO.setLastHandler(userVO.getUserId());
		lpsDAO.update(psVO);
		List<PowerStaffAreaVO> list = lpsaDAO.queryForObject("from PowerStaffAreaVO where staffId = ?", new String[]{id});
		for (PowerStaffAreaVO powerStaffAreaVO : list) {
			powerStaffAreaVO.setDeleteFlag(PowerStaffAreaVO.DELETE_FLAG_YES);
			lpsaDAO.update(powerStaffAreaVO);
		}
	}

	@Override
	public PowerStaffVO findPowerStaffById(String id) throws Exception {
		return lpsDAO.get(id);
	}

	@Override
	public void updatePowerStaff(PowerStaffVO psVO) throws Exception {
		lpsDAO.update(psVO);
	}
}

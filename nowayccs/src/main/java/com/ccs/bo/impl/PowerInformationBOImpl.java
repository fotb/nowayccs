package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IPowerInformationBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.BaseEntity;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;

@Service("powerInforBO")
public class PowerInformationBOImpl implements IPowerInformationBO {
	@Autowired
	private IBaseDAO<PowerStaffVO> lpsDAO; 
	
	@Autowired
	private IBaseDAO<PowerStaffAreaVO> lpsaDAO; 
	
	@Autowired
	private IBaseDAO<PowerInformationVO> piDAO;
	
	@Override
	@Transactional
	public void saveOrUpdate(PowerInformationVO piVO) throws Exception {
		piDAO.save(piVO);
	}
	@Override
	public List<PowerStaffVO> findByAreaSubId(String areaSubId) throws Exception {
		List<PowerStaffAreaVO> psaVOList = lpsaDAO.queryForObject("from PowerStaffAreaVO where deleteFlag=? and  areaSubId = ?", new Object[]{BaseEntity.DELETE_FLAG_NO, areaSubId});
		List<String> psIdList = new ArrayList<String>();
		for (PowerStaffAreaVO psaVO : psaVOList) {
			psIdList.add(psaVO.getStaffId());
		}
		if(psIdList.isEmpty()) {
			return new ArrayList<PowerStaffVO>();
		} else {
			Object[] objs = new Object[psIdList.size()];
			String hql = "from PowerStaffVO t where t.pid in ( ";
			int i = 0;
			for (String id : psIdList) {
				if(i < psIdList.size() - 1){
					hql += "?,";
				} else {
					hql += "?";
				}
				objs[i] = id;
				i++;
			}
			hql += ")";
			List<PowerStaffVO> psVOList = lpsDAO.queryForObject(hql, objs);
			return psVOList;
		}
	}
	
	
	@Override
	public PowerInformationVO findById(String id) {
		return piDAO.get(id);
	}
	@Override
	public List<PowerInformationVO> findByInfoId(String infoId) {
		return piDAO.queryForObject("from PowerInformationVO where informationId = ?", new String[]{infoId});
	}

}

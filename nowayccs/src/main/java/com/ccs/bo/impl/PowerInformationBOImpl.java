package com.ccs.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccs.bo.IPowerInformationBO;
import com.ccs.dao.IAreaDAO;
import com.ccs.dao.IAreaSubDAO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;

public class PowerInformationBOImpl implements IPowerInformationBO {
	@Autowired
	private IBaseDAO<PowerStaffVO> lpsDAO; 
	
	@Autowired
	private IBaseDAO<PowerStaffAreaVO> lpsaDAO; 
	
	@Autowired
	private IBaseDAO<PowerInformationVO> piDAO;
	
	@Autowired
	private IAreaDAO areaDAO;
	
	@Autowired
	private IAreaSubDAO areaSubDAO;
	@Override
	public void saveOrUpdate(PowerInformationVO piVO) throws Exception {
		piDAO.save(piVO);
	}

}

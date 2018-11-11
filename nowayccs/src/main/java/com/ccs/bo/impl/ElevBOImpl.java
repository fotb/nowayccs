package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IElevBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;

@Service("elevBO")
@Transactional
public class ElevBOImpl implements IElevBO {

	@Autowired
	private IBaseDAO<ElevatorVO> elevDAO; 
	
	@Autowired
	private IBaseDAO<ElevHelpInfoVO> elevHelpInfoDAO; 
	
	public  List<ElevatorVO> getElevator(String q)  {
		final String hql = "from ElevatorVO where deviceId like ?";
		List<ElevatorVO> list = elevDAO.queryForObject(hql, new Object[] {"%"+ q + "%"});
		return list;
	}

	@Override
	public void addElevHelp(ElevatorVO eVO, ElevHelpInfoVO ehiVO) throws Exception {
		elevHelpInfoDAO.saveOrUpdate(ehiVO);
	}
}

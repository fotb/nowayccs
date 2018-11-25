package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IElevBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.InformationVO;

@Service("elevBO")
@Transactional
public class ElevBOImpl implements IElevBO {
	
	@Autowired
	private IInformationDAO informatinDAO;

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
	public void addElevHelp(InformationVO iVO, ElevatorVO eVO, ElevHelpInfoVO ehiVO) throws Exception {
		informatinDAO.saveOrUpate(iVO);
		if(null == getElevatorByDeviceId(eVO.getDeviceId())) {
			elevDAO.saveOrUpdate(eVO);
		}
		ehiVO.setInformationId(iVO.getInfoId());
		ehiVO.setElevatorId(eVO.getPid());
		elevHelpInfoDAO.saveOrUpdate(ehiVO);
	}

	@Override
	public ElevatorVO getElevatorByDeviceId(String deviceId) {
		final String hql = "from ElevatorVO where deviceId = ?";
		List<ElevatorVO> list = elevDAO.queryForObject(hql, new Object[] {deviceId});
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}

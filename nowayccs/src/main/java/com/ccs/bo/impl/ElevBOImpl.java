package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IElevBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
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
		ElevatorVO existElevVO = getElevatorByDeviceId(eVO.getDeviceId());
		if(null == existElevVO) {
			elevDAO.save(eVO);
		} else {
			existElevVO.setLastHandler(eVO.getLastHandler());
			existElevVO.setManufacturer(eVO.getManufacturer());
			existElevVO.setNextSurveyDate(eVO.getNextSurveyDate());
			existElevVO.setPosition(eVO.getPosition());
			existElevVO.setReferNo(eVO.getReferNo());
			existElevVO.setSerialNumber(eVO.getSerialNumber());
			existElevVO.setServiceName(eVO.getServiceName());
			existElevVO.setSurveyDate(eVO.getSurveyDate());
			existElevVO.setType(eVO.getType());
			existElevVO.setUpdateDT(eVO.getUpdateDT());
			existElevVO.setUseDept(eVO.getUseDept());
			elevDAO.update(existElevVO);
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

	@Override
	public List<ElevHelpInfoVO> findElevHelpInfoByCreator(String userId) {
		List<InformationVO> infoVOList = informatinDAO.findInfo(userId, Constants.INFOMATION_HELPTYPE_ELEVATOR, Constants.SYS_INFOMATION_STATES_CLZ);
		if(infoVOList.isEmpty()) {
			return new ArrayList<ElevHelpInfoVO>();
		} else {
			String[] parameters = new String[infoVOList.size()];
			String hql = "from ElevHelpInfoVO where informationId in (";
			for (int i = 0; i < infoVOList.size(); i++) {
				parameters[i] = infoVOList.get(0).getInfoId();
				if(i + 1 == infoVOList.size()) {
					hql += "?)";
				} else {
					hql += "?, ";
				}
			}
			return elevHelpInfoDAO.queryForObject(hql, parameters);
		}
		
	}

	@Override
	public ElevatorVO getElevatorByPid(String pid) {
		return elevDAO.get(pid);
	}

	@Override
	public ElevHelpInfoVO getElevHelpInfoByPid(String pid) {
		return elevHelpInfoDAO.get(pid);
	}

	@Override
	public void updateElevHelpInfo(ElevHelpInfoVO elevHelpInfoVO, InformationVO iVO) throws Exception {
		elevHelpInfoDAO.update(elevHelpInfoVO);
		informatinDAO.saveOrUpate(iVO);
	}
}

package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizLifeBO;
import com.ccs.dao.IEntSrvCountDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;

@Service
public class BizLifeBOImpl implements IBizLifeBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Autowired
	private IVolunteerSrvCountDAO volunteerSrvCountDAO;
	
	@Autowired
	private IEntSrvCountDAO entSrvCountDAO;
	
	@Override
	public List<InformationVO> findByCreatorAndStatus(String userId,
			String status, String helpType, PageInfo pageInfo) {
		return informationDAO.findByCreatorAndStatus(userId, status, helpType, pageInfo);
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

	@Override
	public Map<String, String> getVltSrvCount() {
		return volunteerSrvCountDAO.getVolunteerSrvCount();
	}

	@Override
	public Map<String, String> getVltSrvCountToday() {
		return volunteerSrvCountDAO.getVolunteerSrvCountToday();
	}

	@Override
	public Map<String, String> getEntSrvCount() {
		return entSrvCountDAO.getEntSrvCount();
	}

	@Override
	public Map<String, String> getEntSrvCountToday() {
		return entSrvCountDAO.getEntSrvCountToday();
	}

	@Override
	@Transactional
	public void addLifeInfo(LifeInformationVO vo) {
		lifeInformationDAO.saveOrUpdate(vo);
		
		InformationVO infoVO = informationDAO.findById(vo.getInfoId());
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_CLZ);
		informationDAO.saveOrUpate(infoVO);
	}

	@Override
	public void del(String infoId, String userId) {
		InformationVO vo = informationDAO.findById(infoId);
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YQX);
		informationDAO.delete(vo);
		LifeInformationVO lifeInfoVO = lifeInformationDAO.findByInfoId(infoId);
		lifeInfoVO.setCallceler(userId);
		lifeInfoVO.setCallcelTime(new Date());
		lifeInformationDAO.saveOrUpdate(lifeInfoVO);
	}

}
